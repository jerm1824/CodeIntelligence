public class Coche {
    private String nombre;

    private double velocidadActual;
    private double velocidadMaxima;

    private double aceleracion;

    private int manejo;

    private int combustible;
    private int durabilidad;

    private String piloto;

    private Coche(){
        nombre="Relampágo";
        velocidadActual=0;
        velocidadMaxima=1;
        aceleracion=1;
        manejo=1;
        combustible=1;
        durabilidad=1;
        piloto="Franceso virgolini";

    }

    public Coche(String nombre,double velocidadMaxima,double aceleracion,int manejo,int combustible,
                 int durabilidad, String piloto){
        this();
        setNombre(nombre);
        setVelocidadMaxima(velocidadMaxima);
        setAceleracion(aceleracion);
        setManejo(manejo);
        setCombustible(combustible);
        setDurabilidad(durabilidad);
        setPiloto(piloto);
    }

    void acelerar(){
        if (combustible>0){
            velocidadActual += aceleracion;
            if (velocidadActual>velocidadMaxima){
                velocidadActual=velocidadMaxima;
            }
            combustible -= 1;
        }
    }

    void frenar(){
        velocidadActual-=5;
        if (velocidadActual<0){
            velocidadActual=0;
        }

    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public double getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public int getManejo() {
        return manejo;
    }

    public void setManejo(int manejo) {
        this.manejo = manejo;
    }

    public int getCombustible() {
        return combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public int getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(int durabilidad) {
        this.durabilidad = durabilidad;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }
}
