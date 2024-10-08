public class EmpleadoTiempoCompleto extends Empleado{
    private float salarioMensual;
    private int horasTrabajadas;
    @Override
    float calcularSalario() {
        return salarioMensual+(horasTrabajadas*12);
    }

    public float getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(float salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
