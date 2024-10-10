import java.util.Random;

public class Coche {
    private int idCoche;
    private String nombre;
    private int posicion = 1;

    private double velocidadActual;
    private double velocidadMaxima;

    private double aceleracion;

    private int manejo;

    private int combustible;

    private int combustibleMaximo;
    private int durabilidad;

    private String piloto;

    private double distanciaRecorrida;
    private int vueltasCompletadas;
    private boolean enCarrera;
    private double tiempoTotal; // Tiempo total que el coche ha tardado en completar la carrera
    private boolean terminado;

    private Random random;

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

    public Coche(int idCoche, String nombre,double velocidadMaxima,double aceleracion,int manejo,int combustible,
                 int durabilidad, String piloto){
        this();
        setIdCoche(idCoche);
        setNombre(nombre);
        setVelocidadMaxima(velocidadMaxima);
        setAceleracion(aceleracion);
        setManejo(manejo);
        setCombustible(combustible);
        setCombustibleMaximo(combustible);
        setDurabilidad(durabilidad);
        setPiloto(piloto);
        this.enCarrera = true;
        this.random = new Random();
    }

    public void actualizarEstado(Circuito circuito, double tiempoTranscurrido) {
        if (combustible <= 0) {
            enCarrera = false;
            System.out.println(nombre + " se quedó sin combustible.");
            return;
        }
        if (durabilidad <= 0) {
            enCarrera = false;
            System.out.println(nombre + " sufrió daños y no puede continuar.");
            return;
        }

        // Definir la posición de las curvas en la pista (usamos porcentajes de la longitud total de la vuelta)
        double[] posicionesCurvas = circuito.calcularPosicionesCurvas();

        // Actualizar la distancia recorrida
        distanciaRecorrida += velocidadActual * tiempoTranscurrido;

        // Verificar si el coche ha alcanzado una curva
        for (double posicionCurva : posicionesCurvas) {
            if (distanciaRecorrida % circuito.getLongitud() >= posicionCurva &&
                    distanciaRecorrida % circuito.getLongitud() < (posicionCurva + velocidadActual * tiempoTranscurrido)) {
                // Simular la pérdida de velocidad en la curva con un factor de aleatoriedad
                double perdidaPorCurvaBase = (1 - ((double) getManejo() / 10)) * circuito.getDificultad();
                // Rango de aleatoriedad
                double factorAleatorio = 1 + (random.nextDouble() * 0.4 - 0.2); // Rango de 80% a 120%
                double perdidaPorCurva = perdidaPorCurvaBase * factorAleatorio;
                velocidadActual -= velocidadActual * perdidaPorCurva;
                if (velocidadActual < 0) {
                    velocidadActual = 0; // La velocidad no puede ser negativa
                }
                System.out.println(nombre + " ha tomado una curva en el punto " + posicionCurva);
            }
        }

        // Acelerar después de las curvas
        acelerar();

        // Actualizar combustible
        combustible -= 1;

        // Actualizar vueltas completadas
        if (distanciaRecorrida >= circuito.getLongitud() * (vueltasCompletadas + 1)) {
            vueltasCompletadas++;
            System.out.println(nombre + " ha completado la vuelta " + vueltasCompletadas + ".");
        }

        // Actualizar tiempo total
        tiempoTotal += tiempoTranscurrido;
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

    public int getCombustibleMaximo() {
        return combustibleMaximo;
    }

    public void setCombustibleMaximo(int combustibleMaximo) {
        this.combustibleMaximo = combustibleMaximo;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    void turbo(){
        if (getVelocidadActual()<getVelocidadMaxima()){
            System.out.println(getNombre()+ " va a utilizar el turbo.");
            setVelocidadActual(getVelocidadActual()*1.5);
            System.out.println(getNombre()+ " su velocidad actual es: " + getVelocidadActual());
        }
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
                ", combustible maximo=" + combustibleMaximo +
                ", durabilidad=" + durabilidad +
                ", piloto='" + piloto + '\'' +
                '}';
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

    public int getVueltasCompletadas() {
        return vueltasCompletadas;
    }

    public boolean isEnCarrera() {
        return enCarrera;
    }

    public void setEnCarrera(boolean enCarrera) {
        this.enCarrera = enCarrera;
    }

    public boolean haTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public double getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(double tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setVueltasCompletadas(int vueltasCompletadas) {
        this.vueltasCompletadas = vueltasCompletadas;
    }

    public void setDistanciaRecorrida(double distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public double getVelocidadActual() {
        return velocidadActual;
    }

    public void setVelocidadActual(double velocidadActual) {
        if (velocidadActual > velocidadMaxima){
            velocidadActual=velocidadMaxima;
        }
        this.velocidadActual = velocidadActual;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
