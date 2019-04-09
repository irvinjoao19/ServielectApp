package servielect.servielectapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 19/08/2017.
 */

public class ListaTicket {


    private String tecnico;
    @SerializedName("DescripcionProblema")
    private String descripcionProblema;

    public ListaTicket() {
    }

    public ListaTicket(String tecnico, String descripcionProblema) {
        this.tecnico = tecnico;
        this.descripcionProblema = descripcionProblema;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }
}
