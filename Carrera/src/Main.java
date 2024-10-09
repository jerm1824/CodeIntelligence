import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Circuito circuito = new Circuito(2150, 3, 3, "Soleado", 0.5);
        List<Coche> coches = new ArrayList<>();

        Coche coche1 = new Coche("Relámpago", 200, 9, 8, 100, 100, "Piloto 1");
        Coche coche2 = new Coche("Trueno", 190, 10, 7, 100, 100, "Piloto 2");
        coches.add(coche1);
        coches.add(coche2);

        Carrera carrera = new Carrera(circuito, coches, false);
        carrera.iniciarCarrera();
    }
}