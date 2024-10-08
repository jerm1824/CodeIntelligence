public class EmpleadoTiempoParcial extends Empleado{
    private int horasTrabajadas;
    @Override
    float calcularSalario() {
        return horasTrabajadas*12;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
