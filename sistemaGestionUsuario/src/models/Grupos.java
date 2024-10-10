package models;

public class Grupos {

    private int id;

    private String nombre;

    private String descripcion;


    private Grupos(){
        id=1;
        nombre="grupo 1";
        descripcion="descripcion";
    }

    public Grupos(int id,String nombre, String descripcion){
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
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
}
