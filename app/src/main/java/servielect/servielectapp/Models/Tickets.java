package servielect.servielectapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by irvin on 22/8/2017.
 */

public class Tickets {
    @SerializedName("Tickets")
    private List<Ticket> ticketList;


    public Tickets() {
    }

    public Tickets(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
