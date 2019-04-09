package servielect.servielectapp.Models;

/**
 * Created by irvin on 25/8/2017.
 */

public class Ubicacion {

    private String confirmacion;

    public Ubicacion() {
    }

    public Ubicacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
}
