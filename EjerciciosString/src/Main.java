import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
         /*1. Filtrado de elementos pares: Crea una lista de números enteros y utiliza un Stream para filtrar solo los números pares.
       Luego, imprime esos números en pantalla.*/

        List<Integer> numerosEnteros= Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        numerosEnteros.stream().filter(it -> it %2==0).forEach(System.out::println);

       /*2. Cálculo del promedio: A partir de una lista de números, utiliza un Stream para calcular el promedio de los valores.*/

        List<Float> numerosPromedio = Arrays.asList(11f,12f,13f,14f,15f,16f,17f,18f,19f,20f);
        System.out.println(numerosPromedio.stream().reduce(0f,Float::sum)/numerosPromedio.size());
        /*3. Transformación de cadenas: Dada una lista de palabras, utiliza un Stream para convertir todas las palabras a mayúsculas y luego imprímelas.*/

        List<String> palabras = Arrays.asList("hola","adios","que tal","como te va");
        palabras.stream().map(it -> it.toUpperCase()).forEach(System.out::println);

       /* 4. Filtrado por longitud: A partir de una lista de cadenas, utiliza un Stream para filtrar las cadenas que tienen más de 5 caracteres y muestra el resultado.*/

        List<String> cadenasCincoCaracteres = Arrays.asList("Hormigaa","casa","ocho","vehiculo","paseo");
        cadenasCincoCaracteres.stream().filter(it-> it.length()<5).forEach(System.out::println);

       /* 5. Concatenación de cadenas: Dada una lista de nombres, utiliza un Stream para concatenar todos los nombres en una sola cadena, separados por comas.*/
        List<String> nombres = Arrays.asList("Jose","Manuel","Rocio","Lucia","Ana");
        String cadenas = String.valueOf(nombres.stream().reduce((n1, n2)-> n1+","+n2));
        System.out.println(cadenas);
      /*  Conteo de elementos: Crea una lista de números enteros y utiliza un Stream para contar cuántos números son mayores que 10.*/

        List<Integer> numerosMaYoresQue10 = Arrays.asList(11,9,12,1,43,2);
        numerosMaYoresQue10.stream().filter((it-> it>10)).forEach(System.out::println);

      /*  Búsqueda de un elemento: Dada una lista de personas (con nombre y edad), utiliza un Stream para encontrar la primera persona que tenga más de 30 años y muestra su nombre.

        Agrupación de datos: A partir de una lista de personas (con nombre y edad), utiliza un Stream para agrupar las personas en dos listas: las menores y mayores de 18 años.

        Conversión de una lista a un Set: A partir de una lista de números con valores duplicados, utiliza un Stream para eliminar los duplicados y convertir la lista en un Set.

        Filtrado de fechas: Dada una lista de fechas, utiliza un Stream para filtrar solo las fechas posteriores al 1 de enero de 2020 y muestra el resultado.*/
    }
}