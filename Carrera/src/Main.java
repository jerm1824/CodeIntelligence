import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Circuito circuitoSeleccionado=null;
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Coche coche1 = new Coche(1,"Relámpago", 160, 9, 7, 300, 100, "Mario");
        Coche coche2 = new Coche(2,"Snake", 150, 8, 7, 300, 100, "Colapinto");
        Coche coche3 = new Coche(3,"Tanque",170, 10, 7, 300, 100, "Verstappen");
        Circuito circuito1= new Circuito(1,"Circuito 1",600,3,6,"Soleado",1);
        Circuito circuito2=new Circuito(2,"Circuito 2",700,4,7,"Lluvioso",2);

        List<Coche> coches= new ArrayList<>();
        List<Circuito> circuitos= new ArrayList<>();

        coches.add(coche1);coches.add(coche2);coches.add(coche3);
        circuitos.add(circuito1);circuitos.add(circuito2);

        Coche cocheSeleccionado=null;

        System.out.println("Elige un coche");

        for(Coche coche: coches){
            System.out.println(coche.toString());
        }
        do {
            try {
                System.out.println("Introduzca el numero del coche que quiera utilizar: ");
                int idSeleccionado=scan.nextInt();
                for (Coche coche:coches){
                    if (coche.getIdCoche() == idSeleccionado){
                        cocheSeleccionado=coche;
                        break;
                    }
                }
                if (cocheSeleccionado==null){
                    System.out.println("Ha introducido un valor erroneo");
                }
            }catch (InputMismatchException e){
                System.out.println("Introduzca un numero");
                scan.next();
            }
        }while (cocheSeleccionado==null);

        for (Circuito circuito: circuitos){
            System.out.println(circuito.toString());
        }
        do {
            try {
                System.out.println("Introduzca el numero del circuito que quiera utilizar: ");
                int idSeleccionado=scan.nextInt();
                for (Circuito circuito:circuitos){
                    if (circuito.getId() == idSeleccionado){
                        circuitoSeleccionado=circuito;
                        break;
                    }
                }
                if (circuitoSeleccionado==null){
                    System.out.println("Ha introducido un valor erroneo");
                }
            }catch (InputMismatchException e){
                System.out.println("Introduzca un numero");
                scan.next();
            }
        }while (circuitoSeleccionado==null);
        Carrera carrera = new Carrera(circuitoSeleccionado, coches, false);
        carrera.iniciarCarrera();
    }
}