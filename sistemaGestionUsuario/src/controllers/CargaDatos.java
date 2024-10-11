package controllers;

import models.Departamentos;
import models.Grupos;
import models.Roles;
import models.Usuarios;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CargaDatos {

    private static Path obtenerRuta(String fileName) throws IOException, URISyntaxException {
        URL resourceUrl = CargaDatos.class.getClassLoader().getResource(fileName);
        if (resourceUrl == null){
            throw new IOException("Resource not found: " + fileName);
        }
        return Paths.get(resourceUrl.toURI());
    }

    public static List<Usuarios> cargarUsuarios(String fileName) throws IOException, URISyntaxException {
        List<Usuarios> usuarios = new ArrayList<>();
        Path filePath = obtenerRuta(fileName); // Obtener ruta desde resources
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines.subList(1, lines.size())) {
            String[] fields = line.split(",");
            Usuarios usuario = new Usuarios(
                    fields[0],
                    fields[1],
                    fields[2],
                    Integer.parseInt(fields[3]),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public static List<Departamentos> cargarDepartamentos(String fileName) throws IOException, URISyntaxException {
        List<Departamentos> departamentos = new ArrayList<>();
        Path filePath = obtenerRuta(fileName); // Obtener ruta desde resources
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines.subList(1, lines.size())) {
            String[] fields = line.split(",");
            Departamentos departamento = new Departamentos(
                    fields[0],
                    fields[1],
                    fields[2],
                    new ArrayList<>()
            );
            departamentos.add(departamento);
        }
        return departamentos;
    }

    public static List<Roles> cargarRoles(String fileName) throws IOException, URISyntaxException {
        List<Roles> roles = new ArrayList<>();
        Path filePath = obtenerRuta(fileName); // Obtener ruta desde resources
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines.subList(1, lines.size())) {

            String[] fields = line.split(",");
            List<String> permisos = Arrays.asList(fields[2].replace("\"", "").split(","));
            Roles rol = new Roles(
                    (fields[0]),
                    fields[1],
                   permisos
            );
            roles.add(rol);
        }
        return roles;
    }

    public static List<Grupos> cargarGrupos(String fileName) throws IOException, URISyntaxException {
        List<Grupos> grupos = new ArrayList<>();
        Path filePath = obtenerRuta(fileName); // Obtener ruta desde resources
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines.subList(1, lines.size())) {
            String[] fields = line.split(",");
            Grupos grupo = new Grupos(
                    Integer.parseInt(fields[0]),
                    fields[1],
                    fields[2],
                    new ArrayList<>()
            );
            grupos.add(grupo);
        }
        return grupos;
    }
}
