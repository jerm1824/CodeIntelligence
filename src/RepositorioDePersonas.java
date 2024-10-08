import java.util.ArrayList;
import java.util.List;

public class RepositorioDePersonas {
    private List<Persona> listaPersonas=new ArrayList<>();

    void agregarPersonas(Persona persona){
        int cont=0;
        for (Persona persona1 : listaPersonas){
            if (persona1.getId() == persona.getId()){
                cont++;
                break;
            }
        }
        if (cont==0){
            listaPersonas.add(persona);
        }
        else {
            System.out.println("Ese id ya existe");
        }

    }
    void listarPersonas(){
        for(Persona persona : listaPersonas){
            System.out.println(persona);
        }
    }
    void eliminarPersonas(Persona persona){
        for (Persona persona1 : listaPersonas){
            if (persona1.getId() == persona.getId()){
                listaPersonas.remove(persona);
                break;
            }
        }

    }
}
