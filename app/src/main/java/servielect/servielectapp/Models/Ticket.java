package servielect.servielectapp.Models;

import com.google.gson.annotations.SerializedName;


public class Ticket {


    private int TicketId;
    @SerializedName("NroTicket")
    private String NroTicket;
    @SerializedName("EmpresaSede")
    private Sede sede;


    public Ticket() {
    }

    public Ticket(int ticketId, String nroTicket, Sede sede) {
        TicketId = ticketId;
        NroTicket = nroTicket;
        this.sede = sede;
    }

    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int ticketId) {
        TicketId = ticketId;
    }

    public String getNroTicket() {
        return NroTicket;
    }

    public void setNroTicket(String nroTicket) {
        NroTicket = nroTicket;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}