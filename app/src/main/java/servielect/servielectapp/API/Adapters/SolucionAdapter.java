package servielect.servielectapp.API.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import servielect.servielectapp.Models.Solucion;
import servielect.servielectapp.R;

public class SolucionAdapter extends ArrayAdapter<Solucion> {
    private Context context;
    private int layout;
    private List<Solucion> lista;

    public SolucionAdapter(Context context, int layout, List<Solucion> lista) {
        super(context, layout, lista);
        this.context = context;
        this.layout = layout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }




    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.solucion_option, null);

        Solucion solucion = lista.get(position);

        TextView lblSolucionId = (TextView) view.findViewById(R.id.lblSolucionId);
        TextView lblSolucionName = (TextView) view.findViewById(R.id.lblSolucionName);
        TextView lblSolucionDesc = (TextView) view.findViewById(R.id.lblSolucionDesc);

        lblSolucionId.setText(String.valueOf(solucion.getSolucionId()));
        lblSolucionName.setText(solucion.getNombre());
        lblSolucionDesc.setText(solucion.getDescripcion());

        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.solucion_option, null);

        Solucion solucion = lista.get(position);

        TextView lblSolucionId = (TextView) view.findViewById(R.id.lblSolucionId);
        TextView lblSolucionName = (TextView) view.findViewById(R.id.lblSolucionName);
        TextView lblSolucionDesc = (TextView) view.findViewById(R.id.lblSolucionDesc);

        lblSolucionId.setText(String.valueOf(solucion.getSolucionId()));
        lblSolucionName.setText(solucion.getNombre());
        lblSolucionDesc.setText(solucion.getDescripcion());

        return view;
    }


}
