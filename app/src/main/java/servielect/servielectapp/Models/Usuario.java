package servielect.servielectapp.Models;

/**
 * Created by user on 09/08/2017.
 */

public class Usuario {

    private int id;
    private String email;
    private int nivel;
    private String nombre;
    private int activo;


    public Usuario() {
    }

    public Usuario(int id, String email, int nivel, String nombre, int activo) {
        this.id = id;
        this.email = email;
        this.nivel = nivel;
        this.nombre = nombre;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
