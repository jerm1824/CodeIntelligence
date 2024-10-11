package models;

import java.util.List;

public class Usuarios {
    private String id;
    private String nombre;
    private String email;
    private int edad;
    private List<Departamentos> departamentos;
    private List<Roles> roles;
    private List<Grupos> grupos;

    public Usuarios() {}

    public Usuarios(String id, String nombre, String email, int edad, List<Departamentos> departamentos, List<Roles> roles, List<Grupos> grupos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
        this.departamentos = departamentos;
        this.roles = roles;
        this.grupos = grupos;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Departamentos> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamentos> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Roles> getRoles() { return roles; }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Grupos> getGrupos() { return grupos; }

    public void setGrupos(List<Grupos> grupos) { this.grupos = grupos; }

    public String toCsvString() {
        return String.format("%s,%s,%s,%d", id, nombre, email, edad);
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", Nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", departamentos=" + departamentos +
                ", roles=" + roles +
                ", grupos=" + grupos +
                '}';
    }
}
