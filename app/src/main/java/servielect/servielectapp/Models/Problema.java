package servielect.servielectapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by irvin on 22/8/2017.
 */

public class Problema {

    private int ProblemaId;
    @SerializedName("Descripcion")
    private String Descripcion;
    @SerializedName("Categoria")
    private Categoria categoria;

    public Problema() {
    }

    public Problema(int problemaId, String descripcion, Categoria categoria) {
        ProblemaId = problemaId;
        Descripcion = descripcion;
        this.categoria = categoria;
    }

    public int getProblemaId() {
        return ProblemaId;
    }

    public void setProblemaId(int problemaId) {
        ProblemaId = problemaId;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
