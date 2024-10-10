package services;

import controllers.CargaDatos;
import models.Usuarios;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UsuarioService {
    private List<Usuarios> usuarios;

    public UsuarioService(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuarios> obtenerTodosLosUsuarios() {
        return usuarios;
    }

    public Usuarios buscarUsuarioPorId(String id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Usuarios> buscarUsuariosPorNombre(String nombre) {
        return usuarios.stream()
                .filter(u -> u.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Usuarios> obtenerUsuariosPorDepartamento(String departamentoId) {
        return usuarios.stream()
                .filter(u -> u.getDepartamentos().stream()
                        .anyMatch(d -> String.valueOf(d.getId()).equals(departamentoId)))
                .collect(Collectors.toList());
    }

    public void crearUsuario(Usuarios usuario) throws IOException {
        String filePath = obtenerRutaArchivo(); // Obtener ruta desde resources
        usuarios.add(usuario);
        guardarModificaciones(filePath);
    }

    public void actualizarUsuario(Usuarios usuarioActualizado) throws IOException {
        String filePath = obtenerRutaArchivo(); // Obtener ruta desde resources
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuarioActualizado.getId())) {
                usuarios.set(i, usuarioActualizado);
                guardarModificaciones(filePath);
                return;
            }
        }
    }

    public void eliminarUsuario(String id) throws IOException {
        String filePath = obtenerRutaArchivo(); // Obtener ruta desde resources
        usuarios.removeIf(u -> u.getId().equals(id));
        guardarModificaciones(filePath);
    }

    private void guardarModificaciones(String filePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            System.out.println("Guardando usuarios");
            writer.write("id,nombre,email,edad\n"); // Escribir encabezados
            for (Usuarios usuario : usuarios) {
                writer.write(usuario.toCsvString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los usuarios en el CSV: " + e.getMessage());
            throw e; // Lanzar de nuevo la excepción para manejarla en otro lugar si es necesario
        }
    }

    private static Path obtenerRuta(String fileName) throws IOException {
        return Paths.get(Objects.requireNonNull(CargaDatos.class.getClassLoader().getResource(fileName)).getPath());
    }


    private String obtenerRutaArchivo() throws IOException {
        return obtenerRuta("usuarios.csv").toString();
    }
}

