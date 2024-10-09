public class Circuito {
    int id;
    String nombre;
    double longitud;
    int vueltas;
    int curvas;
    String tiempo;
    double dificultad;

    public Circuito(int id,String nombre,double longitud, int vueltas, int curvas, String tiempo, double dificultad) {
        this.id=id;
        this.nombre=nombre;
        this.longitud = longitud;
        this.vueltas = vueltas;
        this.curvas = curvas;
        this.tiempo = tiempo;
        this.dificultad = dificultad;
    }

    public double longitudCarrera() {
        return longitud * vueltas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Circuito{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", longitud=" + longitud +
                ", vueltas=" + vueltas +
                ", curvas=" + curvas +
                ", tiempo='" + tiempo + '\'' +
                ", dificultad=" + dificultad +
                '}';
    }
}
