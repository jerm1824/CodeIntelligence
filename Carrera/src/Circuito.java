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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
