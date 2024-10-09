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

    public double[] calcularPosicionesCurvas() {
        double longitudVuelta = getLongitud();
        double[] posicionesCurvas = new double[getCurvas()];

        // Dividir la longitud de la vuelta en secciones donde estarán las curvas
        for (int i = 0; i < getCurvas(); i++) {
            posicionesCurvas[i] = (longitudVuelta / getCurvas()) * (i + 1);
        }

        return posicionesCurvas;
    }

    public double longitudCarrera() {
        return longitud * vueltas;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getVueltas() {
        return vueltas;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public int getCurvas() {
        return curvas;
    }

    public void setCurvas(int curvas) {
        this.curvas = curvas;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public double getDificultad() {
        return dificultad;
    }

    public void setDificultad(double dificultad) {
        this.dificultad = dificultad;
    }
}