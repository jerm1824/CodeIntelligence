package controllers;

import models.Departamentos;
import models.Grupos;
import models.Roles;
import models.Usuarios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CargaDatos {

    private static Path obtenerRuta(String fileName) throws IOException {
        return Paths.get(Objects.requireNonNull(CargaDatos.class.getClassLoader().getResource(fileName)).getPath());
    }

    public static List<Usuarios> cargarUsuarios(String fileName,
                                                List<Departamentos> todosDepartamentos,
                                                List<Roles> todosRoles,
                                                List<Grupos> todosGrupos) throws IOException {
        List<Usuarios> usuarios = new ArrayList<>();
        Path filePath = obtenerRuta(fileName); // Obtener ruta desde resources
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines.subList(1, lines.size())) { // Omitir el encabezado
            String[] fields = line.split(",");

            // Crear el usuario con los datos básicos
            String id = fields[0];
            String nombre = fields[1];
            String email = fields[2];
            int edad = Integer.parseInt(fields[3]);

            // Leer departamentos
            List<Departamentos> departamentosUsuario = Arrays.stream(fields[4].split("\\|"))
                    .map(depId -> buscarDepartamentoPorId(depId, todosDepartamentos))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Leer roles
            List<Roles> rolesUsuario = Arrays.stream(fields[5].split("\\|"))
                    .map(rolId -> buscarRolPorId(rolId, todosRoles))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Leer grupos
            List<Grupos> gruposUsuario = Arrays.stream(fields[6].split("\\|"))
                    .map(grupoId -> buscarGrupoPorId(grupoId, todosGrupos))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Crear el objeto Usuario con los datos cargados
            Usuarios usuario = new Usuarios(id, nombre, email, edad,
                    departamentosUsuario, rolesUsuario, gruposUsuario);
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public static List<Departamentos> cargarDepartamentos(String fileName) throws IOException {
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

    public static List<Roles> cargarRoles(String fileName) throws IOException {
        List<Roles> roles = new ArrayList<>();
        Path filePath = obtenerRuta(fileName); // Obtener ruta desde resources
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines.subList(1, lines.size())) { // Omitir la cabecera
            String[] fields = line.split(",", 3);

            // Procesar la lista de permisos (eliminar comillas y dividir por comas)
            List<String> permisos = Arrays.asList(fields[2].replace("\"", "").split(","));

            Roles rol = new Roles(
                    fields[0],
                    fields[1],
                    permisos
            );
            roles.add(rol);
        }
        return roles;
    }

    public static List<Grupos> cargarGrupos(String fileName) throws IOException {
        List<Grupos> grupos = new ArrayList<>();
        Path filePath = obtenerRuta(fileName); // Obtener ruta desde resources
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines.subList(1, lines.size())) {
            String[] fields = line.split(",");
            Grupos grupo = new Grupos(
                    fields[0],
                    fields[1],
                    fields[2],
                    new ArrayList<>()
            );
            grupos.add(grupo);
        }
        return grupos;
    }

    private static Departamentos buscarDepartamentoPorId(String depId, List<Departamentos> todosDepartamentos) {
        return todosDepartamentos.stream()
                .filter(dep -> dep.getId().equals(depId))
                .findFirst()
                .orElse(null);
    }

    private static Roles buscarRolPorId(String rolId, List<Roles> todosRoles) {
        return todosRoles.stream()
                .filter(rol -> rol.getId().equals(rolId))
                .findFirst()
                .orElse(null);
    }

    private static Grupos buscarGrupoPorId(String grupoId, List<Grupos> todosGrupos) {
        return todosGrupos.stream()
                .filter(grupo -> grupo.getId().equals(grupoId))
                .findFirst()
                .orElse(null);
    }

}
