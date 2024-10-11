import controllers.CargaDatos;
import models.Departamentos;
import models.Grupos;
import models.Roles;
import models.Usuarios;
import services.DepartamentoService;
import services.RolService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner scan=new Scanner(System.in);
        List<Departamentos> departamentos = CargaDatos.cargarDepartamentos("departamentos.csv");

        List<Roles> roles = CargaDatos.cargarRoles("roles.csv");
        System.out.println(roles);
        List<Grupos> grupos = CargaDatos.cargarGrupos("departamentos.csv");

        List<Usuarios> usuarios = CargaDatos.cargarUsuarios("usuarios.csv");

        DepartamentoService departamentoService=new DepartamentoService(departamentos);
        departamentoService.menu(departamentos);

        RolService rolService=new RolService(roles);
        rolService.menu(roles);

        //Asignar
    }
}