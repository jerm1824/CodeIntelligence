package models;

public class Roles {
    private int id;

    private String nombre;

    private String permisos;

    private Roles(){
        id=1;
        nombre="nombre";
        permisos="permisos";
    }

    public Roles(int id,String nombre, String permisos){
        super();
        setId(id);
        setNombre(nombre);
        setPermisos(permisos);
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

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
}
