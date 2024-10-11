package services;

import controllers.CargaDatos;
import models.Grupos;
import models.Usuarios;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class GrupoService {

    private List<Grupos> grupos;
    private List<Usuarios> usuarios;

    public GrupoService(List<Grupos> grupos, List<Usuarios> usuarios) {
        this.grupos = grupos;
        this.usuarios = usuarios;
    }

    public List<Grupos> obtenerTodosLosGrupos() {
        return grupos;
    }

    public Grupos buscarGrupoPorId(String id) {
        return grupos.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Grupos> buscarGruposPorUsuario(String id){
        if (usuarios == null) {
            throw new IllegalStateException("La lista de usuarios no ha sido inicializada.");
        }
        return Objects.requireNonNull(usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst().orElse(null)).getGrupos();
    }

    public void crearGrupo(Grupos grupo) throws IOException {
        String filePath = obtenerRutaArchivo(); // Obtener ruta desde resources
        grupos.add(grupo);
        guardarModificaciones(filePath);
    }

    public void actualizarGrupo(Grupos grupoActualizado) throws IOException {
        String filePath = obtenerRutaArchivo(); // Obtener ruta desde resources
        for (int i = 0; i < grupos.size(); i++) {
            if (grupos.get(i).getId().equals(grupoActualizado.getId())) {
                grupos.set(i, grupoActualizado);
                guardarModificaciones(filePath);
                return;
            }
        }
    }

    public void eliminarGrupo(String id) throws IOException {
        String filePath = obtenerRutaArchivo(); // Obtener ruta desde resources
        grupos.removeIf(g -> g.getId().equals(id));
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
        return obtenerRuta("grupos.csv").toString();
    }
}
