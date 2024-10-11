import controllers.CargaDatos;
import models.Departamentos;
import models.Grupos;
import models.Roles;
import models.Usuarios;
import services.DepartamentoService;
import services.GrupoService;
import services.RolService;
import services.UsuarioService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Departamentos> departamentos = CargaDatos.cargarDepartamentos("departamentos.csv");
        List<Roles> roles = CargaDatos.cargarRoles("roles.csv");
        List<Grupos> grupos = CargaDatos.cargarGrupos("departamentos.csv");
        List<Usuarios> usuarios = CargaDatos.cargarUsuarios("usuarios.csv", departamentos, roles, grupos);


        UsuarioService usuarioService = new UsuarioService(usuarios);
        GrupoService grupoService = new GrupoService(grupos, usuarios);
        RolService rolService = new RolService(roles,usuarios,departamentos,grupos);
        DepartamentoService departamentoService = new DepartamentoService(departamentos,usuarios);
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int opcionUsuarios;
        int opcionGrupos;
        int opcionDepartamentos;
        int opcionRoles;

        // Imprimir los datos cargados
        usuarios.forEach(System.out::println);
        departamentos.forEach(System.out::println);
        roles.forEach(System.out::println);
        grupos.forEach(System.out::println);

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Operar con Usuarios");
            System.out.println("2. Operar con Grupos");
            System.out.println("3. Operar con Departamentos");
            System.out.println("4. Operar con Roles");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Submenú para operar con usuarios
                    do {
                        System.out.println("\n--- Menú de Usuarios ---");
                        System.out.println("1. Mostrar todos los usuarios");
                        System.out.println("2. Buscar usuario por ID");
                        System.out.println("3. Buscar usuarios por nombre");
                        System.out.println("4. Obtener usuarios por departamento");
                        System.out.println("5. Crear usuario");
                        System.out.println("6. Actualizar usuario");
                        System.out.println("7. Eliminar usuario");
                        System.out.println("0. Volver al Menú Principal");
                        System.out.print("Selecciona una opción: ");
                        opcionUsuarios = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer

                        switch (opcionUsuarios) {
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

                                // Listar departamentos disponibles
                                System.out.println("Departamentos disponibles:");
                                List<Departamentos> departamentosDisponibles = departamentoService.obtenerTodosLosDepartamentos();
                                departamentosDisponibles.forEach(departamento -> System.out.println(departamento.getId() + ": " + departamento.getNombre()));

                                System.out.print("Introduce el IDs de los departamentos separados por comas: ");
                                String[] idsDepartamentos = scanner.nextLine().split(",");
                                List<Departamentos> departamentosSeleccionados = new ArrayList<>();
                                for (String idDepartamento : idsDepartamentos) {
                                    Departamentos departamento = departamentoService.buscarDepartamentoPorId(idDepartamento.trim());
                                    if (departamento != null) {
                                        departamentosSeleccionados.add(departamento);
                                    }
                                }

                                // Listar roles disponibles
                                System.out.println("Roles disponibles:");
                                List<Roles> rolesDisponibles = rolService.obtenerTodosLosRoles();
                                rolesDisponibles.forEach(rol -> System.out.println(rol.getId() + ": " + rol.getNombre()));

                                System.out.print("Introduce los IDs de roles separados por comas: ");
                                String[] idsRoles = scanner.nextLine().split(",");
                                List<Roles> rolesSeleccionados = new ArrayList<>();
                                for (String idRol : idsRoles) {
                                    Roles rol = rolService.buscarRolPorId(idRol.trim());
                                    if (rol != null) {
                                        rolesSeleccionados.add(rol);
                                    }
                                }

                                // Listar grupos disponibles
                                System.out.println("Grupos disponibles:");
                                List<Grupos> gruposDisponibles = grupoService.obtenerTodosLosGrupos();
                                gruposDisponibles.forEach(grupo -> System.out.println(grupo.getId() + ": " + grupo.getNombre()));

                                System.out.print("Introduce los IDs de grupos separados por comas: ");
                                String[] idsGrupos = scanner.nextLine().split(",");
                                List<Grupos> gruposSeleccionados = new ArrayList<>();
                                for (String idGrupo : idsGrupos) {
                                    Grupos grupo = grupoService.buscarGrupoPorId(idGrupo.trim());
                                    if (grupo != null) {
                                        gruposSeleccionados.add(grupo);
                                    }
                                }

                                // Crear el nuevo usuario con los roles y grupos seleccionados
                                Usuarios nuevoUsuario = new Usuarios(nuevoId, nuevoNombre, nuevoEmail, nuevaEdad, departamentosSeleccionados, rolesSeleccionados, gruposSeleccionados);
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

                                    // Listar roles disponibles
                                    System.out.println("Roles disponibles:");
                                    List<Roles> rolesDisponiblesActualizar = rolService.obtenerTodosLosRoles();
                                    rolesDisponiblesActualizar.forEach(rol -> System.out.println(rol.getId() + ": " + rol.getNombre()));

                                    System.out.print("Introduce los IDs de roles separados por comas: ");
                                    String[] idsRolesActualizar = scanner.nextLine().split(",");
                                    List<Roles> rolesSeleccionadosActualizar = new ArrayList<>();
                                    for (String idRol : idsRolesActualizar) {
                                        Roles rol = rolService.buscarRolPorId(idRol.trim());
                                        if (rol != null) {
                                            rolesSeleccionadosActualizar.add(rol);
                                        }
                                    }

                                    // Listar grupos disponibles
                                    System.out.println("Grupos disponibles:");
                                    List<Grupos> gruposDisponiblesActualizar = grupoService.obtenerTodosLosGrupos();
                                    gruposDisponiblesActualizar.forEach(grupo -> System.out.println(grupo.getId() + ": " + grupo.getNombre()));

                                    System.out.print("Introduce los IDs de grupos separados por comas: ");
                                    String[] idsGruposActualizar = scanner.nextLine().split(",");
                                    List<Grupos> gruposSeleccionadosActualizar = new ArrayList<>();
                                    for (String idGrupo : idsGruposActualizar) {
                                        Grupos grupo = grupoService.buscarGrupoPorId(idGrupo.trim());
                                        if (grupo != null) {
                                            gruposSeleccionadosActualizar.add(grupo);
                                        }
                                    }

                                    // Actualizar el usuario con los nuevos datos, roles y grupos seleccionados
                                    usuarioActualizar.setNombre(nuevoNombreActualizar);
                                    usuarioActualizar.setEmail(nuevoEmailActualizar);
                                    usuarioActualizar.setEdad(nuevaEdadActualizar);
                                    usuarioActualizar.setRoles(rolesSeleccionadosActualizar);
                                    usuarioActualizar.setGrupos(gruposSeleccionadosActualizar);

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
                                System.out.println("Volviendo al Menú Principal...");
                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, elige de nuevo.");
                        }
                    } while (opcionUsuarios != 0);
                    break;

                case 2:
                    // Submenú para operar con grupos
                    do {
                        System.out.println("\n--- Menú de Grupos ---");
                        System.out.println("1. Mostrar todos los grupos");
                        System.out.println("2. Buscar grupo por ID");
                        System.out.println("3. Buscar grupos por usuario");
                        System.out.println("4. Crear grupo");
                        System.out.println("5. Actualizar grupo");
                        System.out.println("6. Eliminar grupo");
                        System.out.println("0. Volver al Menú Principal");
                        System.out.print("Selecciona una opción: ");
                        opcionGrupos = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer

                        switch (opcionGrupos) {
                            case 1:
                                // Mostrar todos los grupos
                                System.out.println("\nLista de grupos:");
                                grupoService.obtenerTodosLosGrupos().forEach(System.out::println);
                                break;
                            case 2:
                                // Buscar grupo por ID
                                System.out.print("Introduce el ID del grupo: ");
                                String grupoId = scanner.nextLine();
                                Grupos grupo = grupoService.buscarGrupoPorId(grupoId);
                                System.out.println(grupo != null ? grupo : "Grupo no encontrado.");
                                break;
                            case 3:
                                // Buscar grupos por usuario
                                System.out.print("Introduce el ID del usuario: ");
                                String usuarioId = scanner.nextLine();
                                List<Grupos> gruposPorUsuario = grupoService.buscarGruposPorUsuario(usuarioId);
                                System.out.println("Grupos del usuario:");
                                gruposPorUsuario.forEach(System.out::println);
                                break;
                            case 4:
                                // Crear grupo
                                System.out.print("Introduce el ID del grupo: ");
                                String nuevoGrupoId = scanner.nextLine();
                                System.out.print("Introduce el nombre del grupo: ");
                                String nuevoGrupoNombre = scanner.nextLine();
                                System.out.println("Introduce la descripción del grupo");
                                String nuevoGrupoDescripcion = scanner.nextLine();

                                Grupos nuevoGrupo = new Grupos(nuevoGrupoId, nuevoGrupoNombre, nuevoGrupoDescripcion, new ArrayList<>());
                                grupoService.crearGrupo(nuevoGrupo);
                                System.out.println("Grupo creado: " + nuevoGrupo);
                                break;
                            case 5:
                                // Actualizar grupo
                                System.out.print("Introduce el ID del grupo a actualizar: ");
                                String idGrupoActualizar = scanner.nextLine();
                                Grupos grupoActualizar = grupoService.buscarGrupoPorId(idGrupoActualizar);
                                if (grupoActualizar != null) {
                                    System.out.print("Introduce el nuevo nombre del grupo: ");
                                    String nuevoNombreGrupo = scanner.nextLine();

                                    grupoActualizar.setNombre(nuevoNombreGrupo);

                                    grupoService.actualizarGrupo(grupoActualizar);
                                    System.out.println("Grupo actualizado: " + grupoActualizar);
                                } else {
                                    System.out.println("Grupo no encontrado.");
                                }
                                break;
                            case 6:
                                // Eliminar grupo
                                System.out.print("Introduce el ID del grupo a eliminar: ");
                                String idGrupoEliminar = scanner.nextLine();
                                grupoService.eliminarGrupo(idGrupoEliminar);
                                System.out.println("Grupo eliminado.");
                                break;
                            case 0:
                                System.out.println("Volviendo al Menú Principal...");
                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, elige de nuevo.");
                        }
                    } while (opcionGrupos != 0);
                    break;

                case 3:
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
                        opcionDepartamentos = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcionDepartamentos) {
                            case 1:
                                //Mostrar departamentos
                                System.out.println("\nLista de departamentos:");
                                departamentoService.obtenerTodosLosDepartamentos().forEach(System.out::println);
                                break;

                            case 2:
                                // Buscar departamento por ID
                                System.out.print("Introduce el ID del departamento: ");
                                String id = scanner.nextLine();
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
                                String nombre = scanner.nextLine();
                                List<Departamentos> listaDepartamentosNombre= departamentoService.buscarDepartamentosPorNombre(nombre);
                                listaDepartamentosNombre.forEach(System.out::println);
                                break;

                            /*case 4:
                                // Obtener departamento por usuario
                                System.out.print("Introduce el ID del usuario: ");
                                String usuarioId = scanner.nextLine();
                                List<Departamentos> listaDepartamentosUsuarios= departamentoService.obtenerDepartamentosPorUsuario(usuarioId);
                                listaDepartamentosUsuarios.forEach(System.out::println);
                                break;*/

                            case 4:
                                // Obtener departamento por usuario
                                System.out.print("Introduce el ID del usuario: ");
                                String usuarioId = scanner.nextLine();
                                List<Departamentos> departamentosPorUsuario = departamentoService.obtenerDepartamentosPorUsuario(usuarioId);
                                System.out.println("Departamentos del usuario:");
                                departamentosPorUsuario.forEach(System.out::println);
                                break;


                            case 5:
                                //Crear departamento
                                System.out.print("Introduce el ID del departamento: ");
                                String nuevoId = scanner.nextLine();
                                System.out.print("Introduce el nombre del departamento: ");
                                String nuevoNombre = scanner.nextLine();
                                System.out.print("Introduce la descripción del departamento: ");
                                String nuevaDescripcion = scanner.nextLine();

                                Departamentos departamentos1=new Departamentos(nuevoId,nuevoNombre,nuevaDescripcion, new ArrayList<>());
                                departamentoService.crearDepartamento(departamentos1);
                                break;

                            case 6:
                                // Actualizar departamento
                                System.out.print("Introduce el ID del departamento a actualizar: ");
                                String idActualizar = scanner.nextLine();
                                System.out.print("Introduce el nuevo nombre del departamento: ");
                                String nombreActualizar = scanner.nextLine();
                                System.out.print("Introduce la nueva descripcion del departamento: ");
                                String descripcionActualizar = scanner.nextLine();
                                Departamentos departamentoActualizado= new Departamentos(idActualizar,nombreActualizar,descripcionActualizar, new ArrayList<>());
                                departamentoService.actualizarDepartamento(departamentoActualizado);
                                break;

                            case 7:
                                // Eliminar departamento
                                System.out.print("Introduce el ID del departamento a eliminar: ");
                                String idEliminar = scanner.nextLine();
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

                case 4:
                    do {
                        System.out.println("\n--- Menú de Roles ---");
                        System.out.println("1. Mostrar todos los roles");
                        System.out.println("2. Buscar rol por ID");
                        System.out.println("3. Buscar roles por usuario");
                        System.out.println("4. Crear rol");
                        System.out.println("5. Actualizar rol");
                        System.out.println("6. Eliminar rol");
                        System.out.println("7. Verificar si un usuario posee un permiso");
                        System.out.println("0. Salir");
                        System.out.print("Selecciona una opción: ");
                        opcionRoles = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcionRoles) {
                            case 1:
                                //Mostrar roles
                                System.out.println("\nLista de Roles:");
                                rolService.obtenerTodosLosRoles().forEach(System.out::println);
                                break;
                            case 2:
                                // Buscar roles por ID
                                System.out.print("Introduce el ID del rol: ");
                                String id = scanner.nextLine();
                                Roles compRol= rolService.buscarRolPorId(id);
                                if (compRol==null){
                                    System.out.println("El id introducido no es valido");
                                }
                                else {
                                    System.out.println("El id introducido es del departamento: " + compRol);
                                }
                                break;

                            case 3:
                                // Buscar roles por usuario
                                System.out.print("Introduce el nombre del usuario cuyo rol quiere saber: ");
                                String nombre = scanner.nextLine();
                                List<Roles> listaRolesPorUsuario= rolService.buscarRolesPorUsuario(nombre);
                                listaRolesPorUsuario.forEach(System.out::println);
                                break;

                            case 4:
                                //Crear rol
                                List<String> permisos=new ArrayList<>();
                                System.out.print("Introduce el ID del rol: ");
                                String nuevoId = scanner.nextLine();
                                System.out.print("Introduce el nombre del rol: ");
                                String nuevoNombre = scanner.nextLine();
                                System.out.println("Introduzca los permisos del nuevo rol: ");
                                String permisoNuevo= scanner.nextLine();
                                permisos.add(permisoNuevo);
                                Roles newRol=new Roles(nuevoId,nuevoNombre,permisos);
                                rolService.crealRol(newRol);
                                break;

                            case 5:
                                // Actualizar rol
                                List<String> permisosRol=new ArrayList<>();
                                System.out.print("Introduce el ID del rol a actualizar: ");
                                String idActualizar = scanner.nextLine();
                                System.out.print("Introduce el nuevo nombre del rol: ");
                                String nombreActualizar = scanner.nextLine();
                                System.out.print("Introduce el nuevo permiso del rol: ");
                                String actualizarPermisos = scanner.nextLine();
                                permisosRol.add(actualizarPermisos);
                                Roles rolActualizado= new Roles(idActualizar,nombreActualizar,permisosRol);
                                rolService.actualizarRol(rolActualizado);
                                break;

                            case 6:
                                // Eliminar rol
                                System.out.print("Introduce el ID del rol a eliminar: ");
                                String idEliminar = scanner.nextLine();
                                rolService.eliminarRol(idEliminar);
                                break;

                            case 7:
                                System.out.println("Introduce el ID del usuario cuyos permisos quieres comprobar: ");
                                String idUsuario = scanner.nextLine();
                                System.out.println("Introduce el nombre del permiso que quieres comprobar");
                                String permiso = scanner.nextLine();
                                rolService.verificarPermisoUsuario(idUsuario,permiso);
                                break;

                            case 0:
                                // Salir
                                System.out.println("Saliendo...");
                                break;

                            default:
                                System.out.println("Opción no válida. Por favor, elige de nuevo.");
                        }
                    } while (opcion != 0);
                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}