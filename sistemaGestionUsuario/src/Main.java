import controllers.CargaDatos;
import models.Departamentos;
import models.Grupos;
import models.Roles;
import models.Usuarios;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Usuarios> usuarios = CargaDatos.cargarUsuarios("usuarios.csv");
        List<Departamentos> departamentos = CargaDatos.cargarDepartamentos("departamentos.csv");
        List<Roles> roles = CargaDatos.cargarRoles("roles.csv");
        List<Grupos> grupos = CargaDatos.cargarGrupos("departamentos.csv");

        // Imprimir los datos cargados
        usuarios.forEach(System.out::println);
        departamentos.forEach(System.out::println);
        roles.forEach(System.out::println);
        grupos.forEach(System.out::println);
    }
}