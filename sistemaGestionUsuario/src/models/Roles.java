package models;

import java.util.List;

public class Roles {
    private String id;

    private String nombre;

    private List<String> permisos;

    public Roles(String id,String nombre, List<String> permisos){
        setId(id);
        setNombre(nombre);
        setPermisos(permisos);
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

    public List<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
    }
    public String toCsvString() {
        return String.format("%s,%s,%s", getId(), getNombre(), getPermisos());
    }
    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", permisos=" + permisos +
                '}';
    }
}
