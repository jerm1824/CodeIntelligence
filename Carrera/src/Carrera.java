import java.util.List;

public class Carrera {
    private List<Coche> coches;
    private Circuito circuito;

    public Carrera(List<Coche> coches,Circuito circuito){
        this.coches = coches;
        this.circuito = circuito;
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }
}
