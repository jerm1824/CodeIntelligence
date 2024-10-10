import java.util.List;

public class Juego {
   /* private List<Coche> coches;
    private Circuito circuito;
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
   }*/
}
