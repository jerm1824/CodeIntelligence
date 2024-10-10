import controllers.CargaDatos;
import models.Departamentos;
import models.Grupos;
import models.Roles;
import models.Usuarios;
import services.UsuarioService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Usuarios> usuarios = CargaDatos.cargarUsuarios("usuarios.csv");
        List<Departamentos> departamentos = CargaDatos.cargarDepartamentos("departamentos.csv");
        List<Roles> roles = CargaDatos.cargarRoles("roles.csv");
        List<Grupos> grupos = CargaDatos.cargarGrupos("departamentos.csv");

        UsuarioService usuarioService = new UsuarioService(usuarios);
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Imprimir los datos cargados
        usuarios.forEach(System.out::println);
        departamentos.forEach(System.out::println);
        roles.forEach(System.out::println);
        grupos.forEach(System.out::println);

        do {
            System.out.println("\n--- Menú de Usuario ---");
            System.out.println("1. Mostrar todos los usuarios");
            System.out.println("2. Buscar usuario por ID");
            System.out.println("3. Buscar usuarios por nombre");
            System.out.println("4. Obtener usuarios por departamento");
            System.out.println("5. Crear usuario");
            System.out.println("6. Actualizar usuario");
            System.out.println("7. Eliminar usuario");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Mostrar todos los usuarios
                    System.out.println("\nLista de usuarios:");
                    usuarioService.obtenerTodosLosUsuarios().forEach(System.out::println);
                    break;

                case 2:
                    // Buscar usuario por ID
                    System.out.print("Introduce el ID del usuario: ");
                    String id = scanner.nextLine();
                    Usuarios usuario = usuarioService.buscarUsuarioPorId(id);
                    System.out.println(usuario != null ? usuario : "Usuario no encontrado.");
                    break;

                case 3:
                    // Buscar usuarios por nombre
                    System.out.print("Introduce el nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    List<Usuarios> usuariosPorNombre = usuarioService.buscarUsuariosPorNombre(nombre);
                    System.out.println("Usuarios encontrados:");
                    usuariosPorNombre.forEach(System.out::println);
                    break;

                case 4:
                    // Obtener usuarios por departamento
                    System.out.print("Introduce el ID del departamento: ");
                    String departamentoId = scanner.nextLine();
                    List<Usuarios> usuariosPorDepartamento = usuarioService.obtenerUsuariosPorDepartamento(departamentoId);
                    System.out.println("Usuarios en el departamento:");
                    usuariosPorDepartamento.forEach(System.out::println);
                    break;

                case 5:
                    // Crear usuario
                    System.out.print("Introduce el ID del usuario: ");
                    String nuevoId = scanner.nextLine();
                    System.out.print("Introduce el nombre del usuario: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Introduce el email del usuario: ");
                    String nuevoEmail = scanner.nextLine();
                    System.out.print("Introduce la edad del usuario: ");
                    int nuevaEdad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    Usuarios nuevoUsuario = new Usuarios(nuevoId, nuevoNombre, nuevoEmail, nuevaEdad, new ArrayList<>(), new ArrayList<>());
                    usuarioService.crearUsuario(nuevoUsuario);
                    System.out.println("Usuario creado: " + nuevoUsuario);
                    break;

                case 6:
                    // Actualizar usuario
                    System.out.print("Introduce el ID del usuario a actualizar: ");
                    String idActualizar = scanner.nextLine();
                    Usuarios usuarioActualizar = usuarioService.buscarUsuarioPorId(idActualizar);
                    if (usuarioActualizar != null) {
                        System.out.print("Introduce el nuevo nombre: ");
                        String nuevoNombreActualizar = scanner.nextLine();
                        System.out.print("Introduce el nuevo email: ");
                        String nuevoEmailActualizar = scanner.nextLine();
                        System.out.print("Introduce la nueva edad: ");
                        int nuevaEdadActualizar = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer

                        usuarioActualizar.setNombre(nuevoNombreActualizar);
                        usuarioActualizar.setEmail(nuevoEmailActualizar);
                        usuarioActualizar.setEdad(nuevaEdadActualizar);

                        usuarioService.actualizarUsuario(usuarioActualizar);
                        System.out.println("Usuario actualizado: " + usuarioActualizar);
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 7:
                    // Eliminar usuario
                    System.out.print("Introduce el ID del usuario a eliminar: ");
                    String idEliminar = scanner.nextLine();
                    usuarioService.eliminarUsuario(idEliminar);
                    System.out.println("Usuario eliminado.");
                    break;

                case 0:
                    // Salir
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}