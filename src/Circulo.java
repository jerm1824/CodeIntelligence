public class Circulo extends Figura{
    private double radio;
    final double PI=3.1416;
    @Override
    double calcularArea() {
        return Math.pow(radio*PI,2);
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
}
