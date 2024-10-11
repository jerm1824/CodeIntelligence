import controllers.CargaDatos;
import models.Departamentos;
import models.Grupos;
import models.Roles;
import models.Usuarios;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner scan=new Scanner(System.in);
       // List<Usuarios> usuarios = CargaDatos.cargarUsuarios("usuarios.csv");
        List<Departamentos> departamentos = CargaDatos.cargarDepartamentos("departamentos.csv");
        List<Roles> roles = CargaDatos.cargarRoles("roles.csv");
        List<Grupos> grupos = CargaDatos.cargarGrupos("departamentos.csv");

        // Imprimir los datos cargados
       /* usuarios.forEach(System.out::println);
        departamentos.forEach(System.out::println);
        roles.forEach(System.out::println);
        grupos.forEach(System.out::println);*/
       /* Departamentos departamento1=new Departamentos("3","nombreDep","descripcion departamento",usuarios);
        Departamentos departamento2=new Departamentos("3","nombreDelverdaderpDep","descripcion departamento",usuarios);
        DepartamentoService departamentoService = new DepartamentoService(departamentos);
        departamentoService.obtenerTodosLosDepartamentos();
        departamentoService.crearDepartamento(departamento1);
        departamentoService.buscarDepartamentoPorId("3");
        departamentoService.buscarDepartamentoporNombre("Dep");
        departamentoService.obtenerDepartamentosPorUsuario("1");
        departamentoService.actualizarDepartamento(departamento2);
        departamentoService.obtenerTodosLosDepartamentos();
        departamentoService.eliminarDepartamento(departamento2);
        departamentoService.obtenerTodosLosDepartamentos();*/
        DepartamentoService departamentoService = new DepartamentoService(departamentos);
        int opcion;
        do {
            System.out.println("\n--- Menú de Departamentos ---");
            System.out.println("1. Mostrar todos los departamentos");
            System.out.println("2. Buscar departamento por ID");
            System.out.println("3. Buscar departamento por nombre");
            System.out.println("4. Obtener departamento por usuario");
            System.out.println("5. Crear departamento");
            System.out.println("6. Actualizar departamento");
            System.out.println("7. Eliminar departamento");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scan.nextInt();
            scan.nextLine();
            switch (opcion) {
                case 1:
                    //Mostrar departamentos
                    System.out.println("\nLista de departamentos:");
                    departamentoService.obtenerTodosLosDepartamentos().forEach(System.out::println);
                    break;

                case 2:
                    // Buscar departamento por ID
                    System.out.print("Introduce el ID del departamento: ");
                    String id = scan.nextLine();
                    Departamentos compDepartamento= departamentoService.buscarDepartamentoPorId(id);
                    if (compDepartamento==null){
                        System.out.println("El id introducido no es valido");
                    }
                    else {
                        System.out.println("El id introducido es del departamento: " + compDepartamento);
                    }
                    break;

                case 3:
                    // Buscar departamento por nombre
                    System.out.print("Introduce el nombre del departamento: ");
                    String nombre = scan.nextLine();
                    List<Departamentos> listaDepartamentosNombre= departamentoService.buscarDepartamentosPorNombre(nombre);
                    listaDepartamentosNombre.forEach(System.out::println);
                    break;

                case 4:
                    // Obtener departamento por usuario
                    System.out.print("Introduce el ID del usuario: ");
                    String usuarioId = scan.nextLine();
                    List<Departamentos> listaDepartamentosUsuarios= departamentoService.obtenerDepartamentosPorUsuario(usuarioId);
                    listaDepartamentosUsuarios.forEach(System.out::println);
                    break;

                case 5:
                    //Crear departamento
                    System.out.print("Introduce el ID del departamento: ");
                    String nuevoId = scan.nextLine();
                    System.out.print("Introduce el nombre del departamento: ");
                    String nuevoNombre = scan.nextLine();
                    System.out.print("Introduce la descripción del departamento: ");
                    String nuevaDescripcion = scan.nextLine();

                    Departamentos departamentos1=new Departamentos(nuevoId,nuevoNombre,nuevaDescripcion);
                    departamentoService.crearDepartamento(departamentos1);
                    break;

                case 6:
                    // Actualizar departamento
                    System.out.print("Introduce el ID del departamento a actualizar: ");
                    String idActualizar = scan.nextLine();
                    System.out.print("Introduce el nuevo nombre del departamento: ");
                    String nombreActualizar = scan.nextLine();
                    System.out.print("Introduce la nueva descripcion del departamento: ");
                    String descripcionActualizar = scan.nextLine();
                    Departamentos departamentoActualizado= new Departamentos(idActualizar,nombreActualizar,descripcionActualizar);
                    departamentoService.actualizarDepartamento(departamentoActualizado);
                    break;

                case 7:
                    // Eliminar departamento
                    System.out.print("Introduce el ID del departamento a eliminar: ");
                    String idEliminar = scan.nextLine();
                    departamentoService.eliminarDepartamento(idEliminar);
                    break;

                case 0:
                    // Salir
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige de nuevo.");
            }
        } while (opcion != 0);

    }
}