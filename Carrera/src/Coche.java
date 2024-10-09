public class Coche {
    private int idCoche;
    private String nombre;

    private double velocidadActual;
    private double velocidadMaxima;

    private double aceleracion;

    private int manejo;

    private int combustible;
    private int durabilidad;

    private String piloto;

    private double distanciaRecorrida;

    private Coche(){
        idCoche=1;
        nombre="Relampágo";
        velocidadActual=0;
        velocidadMaxima=1;
        aceleracion=1;
        manejo=1;
        combustible=1;
        durabilidad=1;
        piloto="Franceso virgolini";
        distanciaRecorrida=0;

    }

    public Coche(int idCoche,String nombre,double velocidadMaxima,double aceleracion,int manejo,int combustible,
                 int durabilidad, String piloto){
        this();
        setIdCoche(idCoche);
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

    void avanzar(){
        acelerar();
        if (combustible>0){
            distanciaRecorrida+=velocidadActual;
        }
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getVelocidadActual() {
        return velocidadActual;
    }

    public void setVelocidadActual(double velocidadActual) {
        this.velocidadActual = velocidadActual;
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
        if (durabilidad<0){
            durabilidad=0;
        }
        this.durabilidad = durabilidad;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setDistanciaRecorrida(double distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "Id coche='" + idCoche +
                " nombre='" + nombre +
                " velocidadMaxima=" + velocidadMaxima +
                ", aceleracion=" + aceleracion +
                ", manejo=" + manejo +
                ", combustible=" + combustible +
                ", durabilidad=" + durabilidad +
                ", piloto='" + piloto + '\'' +
                '}';
    }


}
