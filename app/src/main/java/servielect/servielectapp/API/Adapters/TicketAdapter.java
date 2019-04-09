package servielect.servielectapp.API.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import servielect.servielectapp.Models.Ticket;
import servielect.servielectapp.R;


public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    private List<Ticket> tickets;
    private int layout;
    private OnItemClickListener listener;
    private Context context;


    public TicketAdapter(List<Ticket> tickets, int layout, OnItemClickListener listener) {
        this.tickets = tickets;
        this.layout = layout;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(tickets.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNombre;
        public TextView textViewNroTicket;
        public ImageView imageViewEstado;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombre);
            textViewNroTicket = (TextView) itemView.findViewById(R.id.textViewNroTicket);
            imageViewEstado = (ImageView) itemView.findViewById(R.id.imageViewEstado);

        }

        public void bind(final Ticket ticket, final OnItemClickListener listener) {

            textViewNombre.setText(ticket.getSede().getEmpresa().getRazonSocial());
            textViewNroTicket.setText(ticket.getNroTicket());
            //     String condicion = ticket.getEstado();
            imageViewEstado.setImageResource(R.color.colorPrimary);
            //  Picasso.with(context).load(API.BASE_URL_LOGO + 1 + API.FORMATO).into(imageViewEstado);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(ticket, getAdapterPosition());
                }
            });


        }
    }

    public interface OnItemClickListener {
        void onItemClick(Ticket tickets, int position);
    }


}
