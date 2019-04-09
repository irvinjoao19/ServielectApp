package servielect.servielectapp.Models;

/**
 * Created by user on 30/08/2017.
 */

public class Solucion {
    private int SolucionId;
    private String Nombre;
    private String Descripcion;

    public Solucion() {
    }

    public Solucion(int solucionId, String nombre, String descripcion) {
        SolucionId = solucionId;
        Nombre = nombre;
        Descripcion = descripcion;
    }

    public int getSolucionId() {
        return SolucionId;
    }

    public void setSolucionId(int solucionId) {
        SolucionId = solucionId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
