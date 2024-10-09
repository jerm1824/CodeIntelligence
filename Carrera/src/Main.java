import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Coche coche1=new Coche("Relámpapago",200,50,4,50,50,"Mario");
        Coche coche2=new Coche("Snake",150,70,10,50,50,"Colapinto");
        Coche coche3=new Coche("Tanque",100,50,7,100,70,"Verstappen");

        List<Coche> coches= new ArrayList<>();
        coches.add(coche1);coches.add(coche2);coches.add(coche3);

        Circuito circuito=new Circuito(50,3,6,"No se",1);
        Carrera carrera = new Carrera(coches,circuito);
        Juego juego = new Juego(carrera);
        juego.inicioCarrera();
    }
}