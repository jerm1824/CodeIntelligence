import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Carrera {
    private Circuito circuito;
    private List<Coche> coches;
    private boolean climaAdverso;

    public Carrera(Circuito circuito, List<Coche> coches, boolean climaAdverso) {
        this.circuito = circuito;
        this.coches = coches;
        this.climaAdverso = climaAdverso;
    }

    public void iniciarCarrera() {
        double longitudTotal = circuito.longitudCarrera();
        boolean carreraEnCurso = true;
        int tiempo = 0; // Tiempo en segundos o unidades de tiempo

        // Obtener posiciones de las curvas del circuito
        double[] posicionesCurvas = circuito.calcularPosicionesCurvas();

        while (carreraEnCurso) {
            carreraEnCurso = false;

            for (Coche coche : coches) {
                if (coche.isEnCarrera()) {
                    carreraEnCurso = true;

                    // Verificar si el coche ha pasado por una curva
                    for (double posicionCurva : posicionesCurvas) {
                        if (coche.getDistanciaRecorrida() % circuito.getLongitud() >= posicionCurva &&
                                coche.getDistanciaRecorrida() % circuito.getLongitud() < posicionCurva + coche.getVelocidadActual()) {
                            // Simular la pérdida de velocidad en la curva
                            double perdidaPorCurva = (1 - ((double) coche.getManejo() / 10)) * circuito.getDificultad();
                            coche.setVelocidadActual(coche.getVelocidadActual() - coche.getVelocidadActual() * perdidaPorCurva);
                            if (coche.getVelocidadActual() < 0) {
                                coche.setVelocidadActual(0); // La velocidad no puede ser negativa
                            }
                            System.out.println(coche.getNombre() + " ha tomado una curva en el punto " + posicionCurva + ". Velocidad actual: " + coche.getVelocidadActual());
                        }
                    }

                    // Actualizar estado del coche
                    coche.actualizarEstado(circuito, climaAdverso, 1);

                    // Anunciar si el coche ha completado una vuelta
                    if (coche.getDistanciaRecorrida() >= circuito.getLongitud() * (coche.getVueltasCompletadas() + 1)) {
                        coche.setVueltasCompletadas(coche.getVueltasCompletadas() + 1);
                        System.out.println(coche.getNombre() + " ha completado una vuelta " + coche.getVueltasCompletadas() + ".");
                    }

                    // Mostrar estado del coche
                    mostrarEstadoCoche(coche);

                    // Verificar si el coche ha terminado la carrera
                    if (coche.getDistanciaRecorrida() >= longitudTotal) {
                        coche.setEnCarrera(false);
                        coche.setTerminado(true);
                        System.out.println(coche.getNombre() + " ha terminado la carrera en " + coche.getTiempoTotal() + " segundos!");
                    }
                }
            }
            tiempo++;
        }
        mostrarRankingFinal();
    }

    private void mostrarEstadoCoche(Coche coche) {
        System.out.println("Coche: " + coche.getNombre());
        System.out.println("  Posición: " + coche.getDistanciaRecorrida() + " / " + circuito.longitudCarrera());
        System.out.println("  Velocidad Actual: " + coche.getVelocidadActual());
        System.out.println("  Combustible Restante: " + coche.getCombustible());
        System.out.println("  Durabilidad: " + coche.getDurabilidad());
        System.out.println("  Vueltas Restantes: " + (circuito.getVueltas() - coche.getVueltasCompletadas()));
        System.out.println();
    }

    private void mostrarRankingFinal() {
        // Ordenar los coches por tiempo total y filtrar los que no terminaron
        coches.sort(Comparator.comparingDouble(Coche::getTiempoTotal));

        System.out.println("Ranking final:");
        int posicion = 1;
        for (Coche coche : coches) {
            if (coche.haTerminado()) {
                System.out.println(posicion + ". " + coche.getNombre() + " - Tiempo: " + coche.getTiempoTotal() + " segundos");
                posicion++;
            } else {
                System.out.println(coche.getPiloto() + " no terminó la carrera.");
            }
        }

        // Mostrar el ganador
        Coche ganador = coches.stream()
                .filter(Coche::haTerminado)
                .min(Comparator.comparingDouble(Coche::getTiempoTotal))
                .orElse(null);
        if (ganador != null) {
            System.out.println("El ganador es: " + ganador.getPiloto() + " con un tiempo de " + ganador.getTiempoTotal() + " segundos!");
        }
    }
}
