public class Rectangulo extends Figura{
    int base;
    int altura;
    @Override
    double calcularArea() {
        return (base*altura);
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
