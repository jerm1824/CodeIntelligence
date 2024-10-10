package models;

import java.util.List;

public class Departamentos {
    private int id;
    private String nombre;
    private String descripcion;
    private List<Usuarios> usuarios;

    public Departamentos() {
    }

    public Departamentos(int id, String nombre, String descripcion, List<Usuarios> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarios = usuarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Departamentos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", usuarios=" + usuarios +
                '}';
    }
}
