import controllers.CargaDatos;
import models.Departamentos;
import models.Usuarios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DepartamentoService {
    List<Departamentos> departamentosList;

    public DepartamentoService(List<Departamentos> departamentosList) {
        setDepartamentosList(departamentosList);
    }

    public List<Departamentos> getDepartamentosList() {
        return departamentosList;
    }

    public void setDepartamentosList(List<Departamentos> departamentosList) {
        this.departamentosList = departamentosList;
    }

    List<Departamentos> obtenerTodosLosDepartamentos(){
        return getDepartamentosList();
    }

    Departamentos buscarDepartamentoPorId(String id){
        return departamentosList.stream().filter(d-> d.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public List<Departamentos> buscarDepartamentosPorNombre(String nombre){
        return departamentosList.stream().filter(d-> d.getNombre().toLowerCase()
                .contains(nombre.toLowerCase())).collect(Collectors.toList());
    }

    List<Departamentos>obtenerDepartamentosPorUsuario(String idUsuario){
        return departamentosList.stream().filter(d-> d.getUsuarios().
                stream().anyMatch(u -> u.getId().equalsIgnoreCase(idUsuario))).collect(Collectors.toList());
    }

    void crearDepartamento(Departamentos departamento) throws IOException, URISyntaxException {
       for (Departamentos compDepartamento : departamentosList){
           if (compDepartamento.getId().equalsIgnoreCase(departamento.getId())){
               System.out.println("Ese id ya esta en uso");
               return;
           }
       }
       String filePath= obtenerRutaArchivo();
       departamentosList.add(departamento);
       System.out.println("El departamento ha sido añadido");
       guardarModificaciones(filePath);
    }

    void actualizarDepartamento(Departamentos departamento) throws IOException, URISyntaxException {
        String filePath=obtenerRutaArchivo();
        boolean encontrado=false;
        for (int i = 0; i < departamentosList.size(); i++) {
            Departamentos compDepartamento = departamentosList.get(i);
            if (compDepartamento.getId().equalsIgnoreCase(departamento.getId())) {
                departamentosList.set(i, departamento);
                System.out.println("Se ha actualizado el departamento: " + departamento.getNombre());
                guardarModificaciones(filePath);
                encontrado=true;
                break;
            }
        }
        if (!encontrado){
            System.out.println("Ese departamento no existe, no se puede actualizar");
        }
    }

    void eliminarDepartamento(String idDepartamento) throws IOException, URISyntaxException {
        boolean encontrado = false;
        String filePath=obtenerRutaArchivo();
        for (int i = 0; i < departamentosList.size(); i++) {
            Departamentos compDepartamento = departamentosList.get(i);
            if (compDepartamento.getId().equalsIgnoreCase(idDepartamento)) {
                departamentosList.remove(i);
                System.out.println("Departamento eliminado: " + compDepartamento.getNombre());
                guardarModificaciones(filePath);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Error: Departamento no encontrado.");
        }
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
        return obtenerRuta("departamentos.csv").toString();
    }

    private void guardarModificaciones(String filePath) throws IOException, URISyntaxException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            System.out.println("Guardando departamentos");
            writer.write("id,nombre,descripcion\n"); // Escribir encabezados
            for (Departamentos departamento : departamentosList) {
                writer.write(departamento.toCsvString() + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error al guardar los departamentos en el CSV: " + e.getMessage());
            throw e; // Lanzar de nuevo la excepción para manejarla en otro lugar si es necesario
        }

    }



    public void menu(List<Departamentos> departamentos) throws IOException, URISyntaxException {
        Scanner scan=new Scanner(System.in);
        DepartamentoService departamentoService = new DepartamentoService(departamentos);
        int opcion;
        do {
            System.out.println("\n--- Menú de Departamentos ---");
            System.out.println("1. Mostrar todos los departamentos");
            System.out.println("2. Buscar departamento por ID");
            System.out.println("3. Buscar departamento por nombre");
            System.out.println("4. Obtener departamento por usuario");
            System.out.println("5. Crear departamento");
            System.out.println("6. Actualizar departamento");
            System.out.println("7. Eliminar departamento");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scan.nextInt();
            scan.nextLine();
            switch (opcion) {
                case 1:
                    //Mostrar departamentos
                    System.out.println("\nLista de departamentos:");
                    departamentoService.obtenerTodosLosDepartamentos().forEach(System.out::println);
                    break;

                case 2:
                    // Buscar departamento por ID
                    System.out.print("Introduce el ID del departamento: ");
                    String id = scan.nextLine();
                    Departamentos compDepartamento= departamentoService.buscarDepartamentoPorId(id);
                    if (compDepartamento==null){
                        System.out.println("El id introducido no es valido");
                    }
                    else {
                        System.out.println("El id introducido es del departamento: " + compDepartamento);
                    }
                    break;

                case 3:
                    // Buscar departamento por nombre
                    System.out.print("Introduce el nombre del departamento: ");
                    String nombre = scan.nextLine();
                    List<Departamentos> listaDepartamentosNombre= departamentoService.buscarDepartamentosPorNombre(nombre);
                    listaDepartamentosNombre.forEach(System.out::println);
                    break;

                case 4:
                    // Obtener departamento por usuario
                    System.out.print("Introduce el ID del usuario: ");
                    String usuarioId = scan.nextLine();
                    List<Departamentos> listaDepartamentosUsuarios= departamentoService.obtenerDepartamentosPorUsuario(usuarioId);
                    listaDepartamentosUsuarios.forEach(System.out::println);
                    break;

                case 5:
                    //Crear departamento
                    System.out.print("Introduce el ID del departamento: ");
                    String nuevoId = scan.nextLine();
                    System.out.print("Introduce el nombre del departamento: ");
                    String nuevoNombre = scan.nextLine();
                    System.out.print("Introduce la descripción del departamento: ");
                    String nuevaDescripcion = scan.nextLine();

                    Departamentos departamentos1=new Departamentos(nuevoId,nuevoNombre,nuevaDescripcion);
                    departamentoService.crearDepartamento(departamentos1);
                    break;

                case 6:
                    // Actualizar departamento
                    System.out.print("Introduce el ID del departamento a actualizar: ");
                    String idActualizar = scan.nextLine();
                    System.out.print("Introduce el nuevo nombre del departamento: ");
                    String nombreActualizar = scan.nextLine();
                    System.out.print("Introduce la nueva descripcion del departamento: ");
                    String descripcionActualizar = scan.nextLine();
                    Departamentos departamentoActualizado= new Departamentos(idActualizar,nombreActualizar,descripcionActualizar);
                    departamentoService.actualizarDepartamento(departamentoActualizado);
                    break;

                case 7:
                    // Eliminar departamento
                    System.out.print("Introduce el ID del departamento a eliminar: ");
                    String idEliminar = scan.nextLine();
                    departamentoService.eliminarDepartamento(idEliminar);
                    break;

                case 0:
                    // Salir
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige de nuevo.");
            }
        } while (opcion != 0);
    }
}
