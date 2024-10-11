import controllers.CargaDatos;
import models.Departamentos;
import models.Roles;
import models.Usuarios;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class RolService {

    List<Roles> rolesList;

    public RolService(List<Roles>rolesList){
        setRolesList(rolesList);
    }

    public List<Roles> obtenerTodosLosRoles(){
        return getRolesList();
    }

    public Roles buscarRolPorId(String id){
        return rolesList.stream().filter(r -> r.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public List<Roles> buscarRolesPorUsuario(String usuarioId) throws IOException, URISyntaxException {
        List<Usuarios> usuarios= CargaDatos.cargarUsuarios("usuarios.csv");
        return usuarios.stream().filter(u-> u.getId().equals(usuarioId)).
                findFirst().map(Usuarios::getRoles).orElse(Collections.emptyList());
    }

    void crealRol(Roles rol) throws IOException, URISyntaxException{
        for (Roles compRol : rolesList){
            if (compRol.getId().equalsIgnoreCase(rol.getId())){
                System.out.println("Ese id ya esta en uso");
                return;
            }
        }
        String filePath= obtenerRutaArchivo();
        rolesList.add(rol);
        System.out.println("El departamento ha sido añadido");
        guardarModificaciones(filePath);
    }

    void actualizarRol(Roles rol) throws IOException, URISyntaxException {
        String filePath=obtenerRutaArchivo();
        boolean encontrado=false;
        for (int i = 0; i < rolesList.size(); i++) {
            Roles compRol = rolesList.get(i);
            if (compRol.getId().equalsIgnoreCase(rol.getId())) {
                rolesList.set(i, rol);
                System.out.println("Se ha actualizado el rol: " + rol.getNombre());
                guardarModificaciones(filePath);
                encontrado=true;
                break;
            }
        }
        if (!encontrado){
            System.out.println("Ese rol no existe, no se puede actualizar");
        }
    }

    void eliminarRol(String idRol) throws IOException, URISyntaxException {
        boolean encontrado = false;
        String filePath=obtenerRutaArchivo();
        for (int i = 0; i < getRolesList().size(); i++) {
            Roles compRol = rolesList.get(i);
            if (compRol.getId().equalsIgnoreCase(idRol)) {
                rolesList.remove(i);
                System.out.println("Rol eliminado: " + compRol.getNombre());
                guardarModificaciones(filePath);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Error: Rol no encontrado.");
        }
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    private static Path obtenerRuta(String fileName) throws IOException, URISyntaxException {
        URL resourceUrl = CargaDatos.class.getClassLoader().getResource(fileName);
        if (resourceUrl == null){
            throw new IOException("Resource not found: " +  fileName);
        }
        return Paths.get(resourceUrl.toURI());
        // return Paths.get(Objects.requireNonNull(CargaDatos.class.getClassLoader().getResource(fileName)).getPath());
    }


    private String obtenerRutaArchivo() throws IOException, URISyntaxException {
        return obtenerRuta("roles.csv").toString();
    }

    private void guardarModificaciones(String filePath) throws IOException, URISyntaxException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            System.out.println("Guardando roles");
            writer.write("id,nombre,permisos\n"); // Escribir encabezados
            for (Roles rol : rolesList) {
                writer.write(rol.toCsvString() + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error al guardar los departamentos en el CSV: " + e.getMessage());
            throw e; // Lanzar de nuevo la excepción para manejarla en otro lugar si es necesario
        }

    }

    public void menu(List<Roles> rol) throws IOException, URISyntaxException {
        Scanner scan=new Scanner(System.in);
        RolService rolService = new RolService(rol);
        int opcion;
        do {
            System.out.println("\n--- Menú de Roles ---");
            System.out.println("1. Mostrar todos los roles");
            System.out.println("2. Buscar rol por ID");
            System.out.println("3. Buscar roles por usuario");
            System.out.println("4. Crear rol");
            System.out.println("5. Actualizar rol");
            System.out.println("6. Eliminar rol");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scan.nextInt();
            scan.nextLine();
            switch (opcion) {
                case 1:
                    //Mostrar roles
                    System.out.println("\nLista de Roles:");
                    rolService.obtenerTodosLosRoles().forEach(System.out::println);
                    break;
                case 2:
                    // Buscar roles por ID
                    System.out.print("Introduce el ID del rol: ");
                    String id = scan.nextLine();
                    Roles compRol= rolService.buscarRolPorId(id);
                    if (compRol==null){
                        System.out.println("El id introducido no es valido");
                    }
                    else {
                        System.out.println("El id introducido es del departamento: " + compRol);
                    }
                    break;

                case 3:
                    // Buscar roles por usuario
                    System.out.print("Introduce el nombre del usuario cuyo rol quiere saber: ");
                    String nombre = scan.nextLine();
                    List<Roles> listaRolesPorUsuario= rolService.buscarRolesPorUsuario(nombre);
                    listaRolesPorUsuario.forEach(System.out::println);
                    break;

                case 4:
                    //Crear rol
                    List<String> permisos=new ArrayList<>();
                    System.out.print("Introduce el ID del rol: ");
                    String nuevoId = scan.nextLine();
                    System.out.print("Introduce el nombre del rol: ");
                    String nuevoNombre = scan.nextLine();
                    System.out.println("Introduzca los permisos del nuevo rol: ");
                    String permisoNuevo= scan.nextLine();
                    permisos.add(permisoNuevo);
                    Roles newRol=new Roles(nuevoId,nuevoNombre,permisos);
                    rolService.crealRol(newRol);
                    break;

                case 5:
                    // Actualizar rol
                    List<String> permisosRol=new ArrayList<>();
                    System.out.print("Introduce el ID del rol a actualizar: ");
                    String idActualizar = scan.nextLine();
                    System.out.print("Introduce el nuevo nombre del rol: ");
                    String nombreActualizar = scan.nextLine();
                    System.out.print("Introduce el nuevo permiso del rol: ");
                    String actualizarPermisos = scan.nextLine();
                    permisosRol.add(actualizarPermisos);
                    Roles rolActualizado= new Roles(idActualizar,nombreActualizar,permisosRol);
                    rolService.actualizarRol(rolActualizado);
                    break;

                case 6:
                    // Eliminar rol
                    System.out.print("Introduce el ID del rol a eliminar: ");
                    String idEliminar = scan.nextLine();
                    rolService.eliminarRol(idEliminar);
                    break;

                case 7:
                    // Salir
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige de nuevo.");
            }
        } while (opcion != 0);
    }
}
