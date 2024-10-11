package models;

import java.util.List;

public class Departamentos {
    private String id;
    private String nombre;
    private String descripcion;
    private List<Usuarios> usuarios;

    public Departamentos() {
    }

    public Departamentos(String id, String nombre, String descripcion){
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public Departamentos(String id, String nombre, String descripcion, List<Usuarios> usuarios) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setUsuarios(usuarios);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String toCsvString() {
        return String.format("%s,%s,%s,%s", getId(), getNombre(), getDescripcion(),getUsuarios());
    }

    public void menu(){

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
