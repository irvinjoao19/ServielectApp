package servielect.servielectapp.Models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetalleTicket {


    private int ticketId;
    @SerializedName("NroTicket")
    private String nroTicket;
    @SerializedName("Problema")
    private Problema problema;
    @SerializedName("TicketDetalle")
    private List<TicketDetalle> TicketDetalle;
    @SerializedName("Soluciones")
    private List<Solucion> solucions;
    @SerializedName("Estado")
    private String estado;
    @SerializedName("Direccion")
    private String direccion;

    public DetalleTicket() {
    }


    public DetalleTicket(int ticketId, String nroTicket, Problema problema, List<servielect.servielectapp.Models.TicketDetalle> ticketDetalle, List<Solucion> solucions, String estado, String direccion) {
        this.ticketId = ticketId;
        this.nroTicket = nroTicket;
        this.problema = problema;
        TicketDetalle = ticketDetalle;
        this.solucions = solucions;
        this.estado = estado;
        this.direccion = direccion;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(String nroTicket) {
        this.nroTicket = nroTicket;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    public List<servielect.servielectapp.Models.TicketDetalle> getTicketDetalle() {
        return TicketDetalle;
    }

    public void setTicketDetalle(List<servielect.servielectapp.Models.TicketDetalle> ticketDetalle) {
        TicketDetalle = ticketDetalle;
    }

    public List<Solucion> getSolucions() {
        return solucions;
    }

    public void setSolucions(List<Solucion> solucions) {
        this.solucions = solucions;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

