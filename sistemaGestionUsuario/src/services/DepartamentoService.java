package services;

import controllers.CargaDatos;
import models.Departamentos;
import models.Roles;
import models.Usuarios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DepartamentoService {
    List<Departamentos> departamentosList;
    private List<Usuarios> usuarios;

    public DepartamentoService(List<Departamentos> departamentosList, List<Usuarios> usuarios) {
        setDepartamentosList(departamentosList);
        this.usuarios = usuarios;
    }

    public List<Departamentos> getDepartamentosList() {
        return departamentosList;
    }

    public void setDepartamentosList(List<Departamentos> departamentosList) {
        this.departamentosList = departamentosList;
    }

    public List<Departamentos> obtenerTodosLosDepartamentos(){
        return getDepartamentosList();
    }

    public Departamentos buscarDepartamentoPorId(String id){
        return departamentosList.stream().filter(d-> d.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public List<Departamentos> buscarDepartamentosPorNombre(String nombre){
        return departamentosList.stream().filter(d-> d.getNombre().toLowerCase()
                .contains(nombre.toLowerCase())).collect(Collectors.toList());
    }

    public List<Departamentos> obtenerDepartamentosPorUsuario(String id){
        if (usuarios == null) {
            throw new IllegalStateException("La lista de usuarios no ha sido inicializada.");
        }
        return Objects.requireNonNull(usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst().orElse(null)).getDepartamentos();
    }

    public void crearDepartamento(Departamentos departamento) throws IOException, URISyntaxException {
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

    public void actualizarDepartamento(Departamentos departamento) throws IOException, URISyntaxException {
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

    public void eliminarDepartamento(String idDepartamento) throws IOException, URISyntaxException {
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
}
