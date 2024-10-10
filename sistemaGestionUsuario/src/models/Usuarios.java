package models;

import java.util.List;

public class Usuarios {
    private int id;
    private String Nombre;
    private String email;
    private int edad;
    private List<Departamentos> departamentos;
    private List<Roles> roles;

    public Usuarios() {}

    public Usuarios(int id, String nombre, String email, int edad, List<Departamentos> departamentos, List<Roles> roles) {
        this.id = id;
        Nombre = nombre;
        this.email = email;
        this.edad = edad;
        this.departamentos = departamentos;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", departamentos=" + departamentos +
                ", roles=" + roles +
                '}';
    }
}
