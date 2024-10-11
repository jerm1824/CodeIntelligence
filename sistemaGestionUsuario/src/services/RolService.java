package services;

import controllers.CargaDatos;
import models.Departamentos;
import models.Grupos;
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
    private List<Usuarios> usuarios;
    private List<Departamentos> departamentos;
    private List<Grupos> grupos;

    public RolService(List<Roles>rolesList, List<Usuarios> usuarios, List<Departamentos> departamentos, List<Grupos> grupos){
        setRolesList(rolesList);
        this.usuarios = usuarios;
        this.departamentos = departamentos;
        this.grupos = grupos;
    }

    public List<Roles> obtenerTodosLosRoles(){
        return getRolesList();
    }

    public Roles buscarRolPorId(String id){
        return rolesList.stream().filter(r -> r.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public List<Roles> buscarRolesPorUsuario(String id){
        if (usuarios == null) {
            throw new IllegalStateException("La lista de usuarios no ha sido inicializada.");
        }
        return Objects.requireNonNull(usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst().orElse(null)).getRoles();
    }

    public void crealRol(Roles rol) throws IOException, URISyntaxException{
        for (Roles compRol : rolesList){
            if (compRol.getId().equalsIgnoreCase(rol.getId())){
                System.out.println("Ese id ya esta en uso");
                return;
            }
        }
        String filePath= obtenerRutaArchivo();
        rolesList.add(rol);
        System.out.println("El rol ha sido añadido");
        guardarModificaciones(filePath);
    }

    public void actualizarRol(Roles rol) throws IOException, URISyntaxException {
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

    public void eliminarRol(String idRol) throws IOException, URISyntaxException {
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

    public boolean verificarPermisoUsuario(String usuarioId, String permiso) throws IOException, URISyntaxException {
        List<Usuarios> usuarios = CargaDatos.cargarUsuarios("usuarios.csv", departamentos, rolesList, grupos);
        for (Usuarios compUsuario : usuarios) {
            if (compUsuario.getId().equals(usuarioId)) {
                for (Roles rol : compUsuario.getRoles()) {
                    if (rol.getPermisos().contains(permiso)) {
                        System.out.println("El usuario posee el permiso");
                        return true;
                    }
                }
            }
        }
        System.out.println("El usuario no posee el permiso");
        return false;
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
            System.err.println("Error al guardar los roles en el CSV: " + e.getMessage());
            throw e; // Lanzar de nuevo la excepción para manejarla en otro lugar si es necesario
        }

    }
}
