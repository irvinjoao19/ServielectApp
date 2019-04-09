package servielect.servielectapp.Models;

/**
 * Created by user on 10/07/2017.
 */

public class Cliente {

    private String correo;
    private String pass;
    private String name;
    private String confirmacion;

    public Cliente() {
    }

    public Cliente(String correo,String pass,String name,String confirmacion) {
        this.correo = correo;
        this.pass = pass;
        this.name = name;
        this.confirmacion = confirmacion;

    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
}
