import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
      /*
      * 1.Crea una jerarquía de clases que represente diferentes tipos de animales. La clase base será Animal, con un método hacerSonido(). Hereda de ella clases como Perro y Gato,
      *  sobrescribiendo el método hacerSonido() para que cada subclase produzca su sonido característico.
        Clases Finales:*/
        Animal animal=new Animal();
        Gato gato=new Gato();
        Perro perro= new Perro();
        animal.hacerSonido();
        gato.hacerSonido();
        perro.hacerSonido();

       /* 2.Implementa una clase Vehiculo que sea final y contenga un método arrancar(). Intenta crear una subclase que herede de Vehiculo y observa el error que se genera.
        *  Explica por qué no se puede heredar de una clase final.
        Clases Abstractas y Métodos Abstractos:*/

        // Al definir una clase como final se entiende que después de esta no habra ninguna clase más, por lo que ninguna otra clase deberia de heredar de ella.
        // No esta puesto que Coche herede de vehiculo para que no haya errores en el programa
        /*3.Define una clase abstracta Figura con un método abstracto calcularArea(). Luego, crea subclases como Circulo y Rectangulo que implementen este método para calcular el
        *  área según su tipo. Finalmente, crea un programa que permita crear figuras y calcular sus áreas.
        Uso de Interfaces:*/

        Circulo circulo=new Circulo();
        Rectangulo rectangulo=new Rectangulo();
        Scanner scan=new Scanner(System.in);
        System.out.println("Introduzca el radio del circulo");
        circulo.setRadio(scan.nextDouble());
        System.out.println("El área del circulo es: " + circulo.calcularArea());
        System.out.println("Introduzca la base del rectángulo");
        rectangulo.setBase(scan.nextInt());
        System.out.println("Introduzca la base del rectángulo");
        rectangulo.setAltura(scan.nextInt());
        System.out.println("El área del rectángulo es: " + rectangulo.calcularArea());
        scan.nextLine(); // Intellij tiene un bug que cuando haces un scan de int y luego uno de String no cuenta el scan de String,
                         // Este scan lo creo para que el programa se lo salte y no cause un error ya que el siguiente scan es de String.
        /*4.Crea una interfaz Vehiculo con los métodos acelerar() y frenar(). Implementa esta interfaz en una clase Coche.
         Luego, crea una instancia de Coche y llama a los métodos acelerar() y frenar().
        Manejo de Enums:*/

        Coche coche = new Coche();
        coche.acelerar();
        coche.frenar();

        /*5.Crea un enum que represente los días de la semana (LUNES, MARTES, etc.). Implementa un programa que permita al usuario ingresar un día y responda
        * si es un día laboral o un fin de semana utilizando una estructura switch.
        Polimorfismo:*/

        System.out.println("Introduzca un dia de la semana");
        String diaUsuario= scan.nextLine().toUpperCase();

        Dias dia = Dias.valueOf(diaUsuario);
        if (dia.esDiaLaboral()){
            System.out.println("Es un dia laboral");
        }
        else {
            System.out.println("No es un dia laboral");
        }

        /*6.Implementa una clase abstracta Empleado que tenga un método abstracto calcularSalario(). Luego, crea las clases EmpleadoTiempoCompleto y EmpleadoTiempoParcial
        * que hereden de Empleado y sobrescriban el método calcularSalario() según las horas trabajadas o el salario mensual.
         Crea instancias de ambos tipos de empleados y calcula sus salarios.
        Repositorio de Datos en Memoria:*/

        EmpleadoTiempoCompleto empleadoTiempoCompleto=new EmpleadoTiempoCompleto();
        empleadoTiempoCompleto.setHorasTrabajadas(160);
        empleadoTiempoCompleto.setSalarioMensual(1000);
        System.out.println("El empleado a tiempo completo cobra: " + empleadoTiempoCompleto.calcularSalario());
        EmpleadoTiempoParcial empleadoTiempoParcial=new EmpleadoTiempoParcial();
        empleadoTiempoParcial.setHorasTrabajadas(160);
        System.out.println("El empleado a tiempo parcial cobra: " + empleadoTiempoParcial.calcularSalario());

       /* 7.Implementa una clase RepositorioDePersonas que permita agregar, eliminar y listar objetos de tipo Persona.
         El repositorio debe almacenar
        * las personas en una lista interna y proveer métodos para agregar y y eliminar personas por su id.*/

        Persona persona= new Persona(1,"Paco");
        Persona persona1= new Persona(2,"Maria");
        Persona persona2 = new Persona(3,"Jose");
        Persona persona3 = new Persona(1,"Jose Luis");

        RepositorioDePersonas repositorioDePersonas= new RepositorioDePersonas();
        repositorioDePersonas.agregarPersonas(persona);
        repositorioDePersonas.agregarPersonas(persona1);
        repositorioDePersonas.agregarPersonas(persona2);
        repositorioDePersonas.agregarPersonas(persona3);
        repositorioDePersonas.listarPersonas();
        repositorioDePersonas.eliminarPersonas(persona2);
        repositorioDePersonas.listarPersonas();

    }
}