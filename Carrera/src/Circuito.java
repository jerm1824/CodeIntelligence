public class Circuito {
    double longitud;
    int vueltas;
    int curvas;
    String tiempo;
    double dificultad;

    public Circuito(double longitud, int vueltas, int curvas, String tiempo, double dificultad) {
        this.longitud = longitud;
        this.vueltas = vueltas;
        this.curvas = curvas;
        this.tiempo = tiempo;
        this.dificultad = dificultad;
    }

    public double longitudCarrera() {
        return longitud * vueltas;
    }
}
