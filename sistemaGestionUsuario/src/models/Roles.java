package models;

import java.util.List;

public class Roles {
    private int id;

    private String nombre;

    private List<String> permisos;

    private Roles(){
    }

    public Roles(int id,String nombre, List<String> permisos){
        this.id = id;
        this.nombre = nombre;
        this.permisos = permisos;
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

    public List<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
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
