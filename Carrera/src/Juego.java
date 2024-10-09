import java.util.List;
import java.util.Random;

public class Juego {
    Carrera carrera;
    public Juego(Carrera carrera){
        this.carrera=carrera;
    }

    public void inicioCarrera(){
        int vueltas=carrera.getCircuito().vueltas;
        while (vueltas > 0){
            for( Coche coche: carrera.getCoches()){
                coche.avanzar();
                System.out.println("El coche: " + coche.getNombre());
                System.out.println("Velocidad actual: " + coche.getVelocidadActual());
                System.out.println("Combustible restante: " + coche.getCombustible());
                System.out.println("Durabilidad: " + coche.getDurabilidad());
                distanciaRecorridaCoche(coche,carrera.getCircuito());
                System.out.println("Ha recorrido " + coche.getDistanciaRecorrida() + " en la vuelta: " + vueltas);
                eventosAleatorios(coche,carrera.getCircuito());
            }
            System.out.println("Quedan " + vueltas + " vueltas");
            vueltas--;
        }
    }

   public void distanciaRecorridaCoche(Coche coche, Circuito circuito){
        if (coche.getDistanciaRecorrida()> circuito.longitud){
            coche.setDistanciaRecorrida(coche.getDistanciaRecorrida()-circuito.longitud);
        }
        else {
            coche.setDistanciaRecorrida(coche.getDistanciaRecorrida());
        }
   }

   public void eventosAleatorios(Coche coche,Circuito circuito){
       Random rand=new Random();
       int eventoAleatorio= rand.nextInt();;
       double dificultad=circuito.getDificultad();
       if (dificultad==0){
           if (eventoAleatorio>50 && eventoAleatorio<=80){
               System.out.println(coche.getNombre() + "Ha sufrido un ligero choque");
               coche.setDurabilidad(coche.getDurabilidad()-10);
               System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
           }
           else if (eventoAleatorio>80){
               System.out.println(coche.getNombre() + " Ha sufrido un golpe grave");
               coche.setDurabilidad(coche.getDurabilidad()-20);
               System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
           }
       }
       else if (dificultad==1){
           if (eventoAleatorio>25&& eventoAleatorio<=50){
               System.out.println(coche.getNombre() + " ha sufrido un ligero golpe");
               coche.setDurabilidad(coche.getDurabilidad()-10);
               System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
           } else if (eventoAleatorio>50 && eventoAleatorio <=90) {
               System.out.println(coche.getNombre() + " ha sifrido un golpe grave");
               coche.setDurabilidad(coche.getDurabilidad()-20);
               System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());

           } else if (eventoAleatorio>90) {
               System.out.println(coche.getNombre() + " ha sufrido un golpe muy grave");
               coche.setDurabilidad(coche.getDurabilidad()-40);
               System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
           }
       }
       else {
           if (eventoAleatorio>10 && eventoAleatorio<25){
               System.out.println(coche.getNombre() + " ha sufrido un ligero golpe");
               coche.setDurabilidad(coche.getDurabilidad()-10);
               System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
           }
           else if (eventoAleatorio>=25 && eventoAleatorio<50) {
               System.out.println(coche.getNombre() + " ha sifrido un golpe grave");
               coche.setDurabilidad(coche.getDurabilidad()-20);
               System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
           }
           else if (eventoAleatorio>=50 && eventoAleatorio<95) {
               System.out.println(coche.getNombre() + " ha sufrido un golpe muy grave");
               coche.setDurabilidad(coche.getDurabilidad()-40);
               System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
           }
           else if (eventoAleatorio>95){
               System.out.println(coche.getNombre() + " ha sufrido un siniestro total");
               coche.setDurabilidad(coche.getDurabilidad()-100);
               System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
           }
       }

   }
}
