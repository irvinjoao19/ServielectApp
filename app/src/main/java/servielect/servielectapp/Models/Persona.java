package servielect.servielectapp.Models;

/**
 * Created by user on 30/07/2017.
 */

public class Persona {

    private int id;
    private String name;
    private String correo;
    private String address;

    public Persona() {

    }

    public Persona(int id, String name, String correo, String address) {
        this.id = id;
        this.name = name;
        this.correo = correo;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
