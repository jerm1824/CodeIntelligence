import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Carrera {
    private Circuito circuito;
    private List<Coche> coches;

    private Coche cocheJugador;
    Random rand = new Random();

    public Carrera(Circuito circuito, List<Coche> coches,Coche cocheJugador) {
        this.circuito = circuito;
        this.coches = coches;
        this.cocheJugador = cocheJugador;

        Climas clima = Climas.valueOf(circuito.getTiempo().toUpperCase()); // Convertir String a enum
        ajustarDificultadPorClima(clima);
    }

    public void iniciarCarrera() {
        System.out.println(circuito);
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
                            System.out.println(coche.getNombre() + " ha tomado una curva en el punto " + posicionCurva);
                        }
                    }

                    // Actualizar estado del coche
                    double probabilidadEvento = calcularProbabilidad(circuito.getDificultad());
                    double eventoAleatorio = rand.nextDouble();
                    if (eventoAleatorio <= probabilidadEvento) {
                        eventosAleatorios(coche, circuito);
                    }

                    // Activar el turbo si está por debajo de la mitad de combustible
                    if (coche.getCombustible()< coche.getCombustibleMaximo()/2) {
                        if (eventoAleatorio<0.2){
                            coche.turbo();
                        }

                    }
                    coche.actualizarEstado(circuito, 1);

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
            actualizarPosiciones();
            tiempo++;
        }
        mostrarRankingFinal();
    }

    private void mostrarEstadoCoche(Coche coche) {
        System.out.println("Coche: " + coche.getNombre());
        System.out.println("Posición: P" + coche.getPosicion());
        System.out.println("  Distancia Recorrida: " + coche.getDistanciaRecorrida() + " / " + circuito.longitudCarrera());
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("  Velocidad Actual: " + df.format(coche.getVelocidadActual()) + "KM/H");
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
            comprobarGanador(getCocheJugador(),ganador);
        }
    }

    private void ajustarDificultadPorClima(Climas clima) {
        switch (clima) {
            case NUBLADO:
                circuito.setDificultad(circuito.getDificultad() + 1);
                System.out.println("El clima es adverso (" + clima + "). La dificultad aumenta en 1.");
                break;
            case LLUVIOSO:
                circuito.setDificultad(circuito.getDificultad() + 2);
                System.out.println("El clima es bastante adverso (" + clima + "). La dificultad aumenta en 2.");
                break;
            case NEVADO:
                circuito.setDificultad(circuito.getDificultad() + 3);
                System.out.println("El clima es extremadamente adverso (" + clima + "). La dificultad aumenta en 3.");
                break;
            case SOLEADO:
                System.out.println("El clima es favorable.");
                break;
        }
    }

    // Simulación de accidentes
    public void eventosAleatorios(Coche coche,Circuito circuito) {
        Random rand = new Random();
        int eventoAleatorio = rand.nextInt(101);
        System.out.println(eventoAleatorio);
        ;
        double dificultad = circuito.getDificultad();
        if (dificultad == 0) {
            if (eventoAleatorio > 50 && eventoAleatorio <= 80) {
                System.out.println(coche.getNombre() + "Ha sufrido un ligero choque");
                coche.setDurabilidad(coche.getDurabilidad() - 5);
                System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
            } else if (eventoAleatorio > 80) {
                System.out.println(coche.getNombre() + " Ha sufrido un golpe grave");
                coche.setDurabilidad(coche.getDurabilidad() - 10);
                System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
            }
        } else if (dificultad == 1) {
            if (eventoAleatorio > 25 && eventoAleatorio <= 50) {
                System.out.println(coche.getNombre() + " ha sufrido un ligero golpe");
                coche.setDurabilidad(coche.getDurabilidad() - 5);
                System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
            } else if (eventoAleatorio > 50 && eventoAleatorio <= 90) {
                System.out.println(coche.getNombre() + " ha sifrido un golpe grave");
                coche.setDurabilidad(coche.getDurabilidad() - 10);
                System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());

            } else if (eventoAleatorio > 90) {
                System.out.println(coche.getNombre() + " ha sufrido un golpe muy grave");
                coche.setDurabilidad(coche.getDurabilidad() - 30);
                System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
            }
        } else {
            if (eventoAleatorio > 10 && eventoAleatorio < 25) {
                System.out.println(coche.getNombre() + " ha sufrido un ligero golpe");
                coche.setDurabilidad(coche.getDurabilidad() - 5);
                System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
            } else if (eventoAleatorio >= 25 && eventoAleatorio < 50) {
                System.out.println(coche.getNombre() + " ha sifrido un golpe grave");
                coche.setDurabilidad(coche.getDurabilidad() - 10);
                System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
            } else if (eventoAleatorio >= 50 && eventoAleatorio < 95) {
                System.out.println(coche.getNombre() + " ha sufrido un golpe muy grave");
                coche.setDurabilidad(coche.getDurabilidad() - 25);
                System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
            } else if (eventoAleatorio > 95) {
                System.out.println(coche.getNombre() + " ha sufrido un siniestro total");
                coche.setDurabilidad(coche.getDurabilidad() - 100);
                System.out.println(coche.getNombre() + " su durabilidad actual es: " + coche.getDurabilidad());
            }
        }
    }

    // Probabilidad de eventos según dificultad
    private double calcularProbabilidad(int dificultad) {
        switch (dificultad) {
            case 1: // Dificultad 1
                return 0.02; // 2% de probabilidad
            case 2: // Dificultad 2
                return 0.05; // 5% de probabilidad
            case 3:
                return 0.07; // 7% de probabilidad
            case 4:
                return 0.1; // 10% de probabilidad
            default:
                return 0.0; // Por defecto, sin eventos
        }
    }

    // Comprobar la posición en carrera del coche
    public void actualizarPosiciones() {
        // Primero, restablece las posiciones de todos los coches
        for (Coche coche : coches) {
            coche.setPosicion(0); // O establece a un valor que indique no asignada
        }

        // Luego, calcula la posición de cada coche
        for (Coche coche : coches) {
            int delante = 0; // Contador de coches delante

            for (Coche otroCoche : coches) {
                if (otroCoche != coche) {
                    if (otroCoche.getDistanciaRecorrida() > coche.getDistanciaRecorrida()) {
                        delante++; // Incrementa si otro coche está delante
                    }
                }
            }

            coche.setPosicion(delante + 1); // Establece la posición del coche
        }
    }


    // Comprobar si el jugador ha ganado la carrera
    void comprobarGanador(Coche cocheSeleccionado,Coche cocheGanador){
        if (cocheSeleccionado.getIdCoche()==cocheGanador.getIdCoche()){
            System.out.println("Su coche ha ganado.!FELICIDADES'");
        }
        else {
            System.out.println("No has ganado, intentalo otra vez");
        }

    }

    public Coche getCocheJugador() {
        return cocheJugador;
    }

    public void setCocheJugador(Coche cocheJugador) {
        this.cocheJugador = cocheJugador;
    }
}
