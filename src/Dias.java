public enum Dias {
    LUNES,MARTES,MIERCOLES,JUEVES,VIERNES,SABADO,DOMINGO;

    public boolean esDiaLaboral() {
        switch (this) {
            case LUNES:
            case MARTES:
            case MIERCOLES:
            case JUEVES:
            case VIERNES:
                return true;
            case SABADO:
            case DOMINGO:
                return false;
        }
        return false;
    }
}
