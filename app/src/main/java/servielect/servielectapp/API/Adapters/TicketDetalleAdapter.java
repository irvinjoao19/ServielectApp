package servielect.servielectapp.API.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import servielect.servielectapp.API.API;
import servielect.servielectapp.Models.TicketDetalle;
import servielect.servielectapp.R;


public class TicketDetalleAdapter extends RecyclerView.Adapter<TicketDetalleAdapter.ViewHolder> {

    private List<TicketDetalle> listaTickets;
    private int layout;
    private OnItemClickListener listener;
    private Context context;


    public TicketDetalleAdapter(List<TicketDetalle> listaTickets, int layout, OnItemClickListener listener) {
        this.listaTickets = listaTickets;
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
        holder.bind(listaTickets.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return listaTickets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNombre;
        public TextView textViewDetalleProblema;
        public ImageView imageViewNombre;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombre);
            textViewDetalleProblema = (TextView) itemView.findViewById(R.id.textViewDetalleProblema);
            imageViewNombre = (ImageView) itemView.findViewById(R.id.imageViewNombre);
        }

        public void bind(final TicketDetalle listaTicket, final OnItemClickListener listener) {
            Picasso.with(context).load(API.BASE_URL_LOGO + listaTicket.getUsuarioId() + API.FORMATO).into(imageViewNombre);
            textViewNombre.setText(listaTicket.getTecnico());
            textViewDetalleProblema.setText(listaTicket.getDescripcion());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(listaTicket, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TicketDetalle listaTicket, int position);
    }
}
