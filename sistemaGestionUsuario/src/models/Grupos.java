package models;

import java.util.List;

public class Grupos {

    private int id;

    private String nombre;

    private String descripcion;

    private List<Usuarios> listaUsuarios;


    private Grupos(){
        id=1;
        nombre="grupo 1";
        descripcion="descripcion";
    }

    public Grupos(int id,String nombre, String descripcion,List<Usuarios> listaUsuarios){
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setListaUsuarios(listaUsuarios);
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

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public String toString() {
        return "Grupos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", listaUsuarios=" + listaUsuarios +
                '}';
    }
}
