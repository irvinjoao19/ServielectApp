package servielect.servielectapp.Activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import servielect.servielectapp.API.API;
import servielect.servielectapp.API.APIServices.TicketServices;
import servielect.servielectapp.API.Adapters.SolucionAdapter;
import servielect.servielectapp.API.Adapters.TicketDetalleAdapter;
import servielect.servielectapp.Models.DetalleTicket;
import servielect.servielectapp.Models.Solucion;
import servielect.servielectapp.Models.TicketDetalle;
import servielect.servielectapp.R;
import servielect.servielectapp.Utils.Util;

public class TicketDetalleActivity extends AppCompatActivity {

    private SharedPreferences prefs, usuario;

    private TextView textViewNroTicketTitulo;
    private TextView textViewNroTicket;
    private TextView textViewCategoria;
    private TextView textViewTituloCategoria;
    private TextView textViewTituloSolucion;
    private TextView textViewProblema;
    private TextView textViewTituloProblema;
    private TextView textViewTituloDireccion;
    private TextView textViewDireccion;


    private EditText editTextDescripcion;
    private EditText editTextNombreDescripcion;
    private EditText editTextDescripcionProblema;

    private ProgressBar progressBarLoad;
    private Spinner spinnerSolucion;

    //private FloatingActionButton buttonAtender;
    private Button buttonAtender;
    private Button buttonEmpezar;
    private Button buttonFinalizar;
    private Button buttonCancelar;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter RAdapter;

    private Call<TicketDetalle> atenderCall;
    private Call<DetalleTicket> detalleTicketCall;
    private TicketServices ticketServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detalle);

        prefs = getSharedPreferences("TicketPreferences", Context.MODE_PRIVATE);
        usuario = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Bundle bundle2 = getIntent().getExtras();
        String nombre = bundle2.getString("nombre");

        setToolbar(nombre);
        DeclareDatos();

        ticketServices = API.getAPI().create(TicketServices.class);
        Cargar cargar = new Cargar();
        cargar.execute();

    }

    private void setToolbar(String nombre) {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(nombre);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void DeclareDatos() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDetalle);
        layoutManager = new LinearLayoutManager(this);
        textViewNroTicketTitulo = (TextView) findViewById(R.id.textViewNroTicketTitulo);
        textViewNroTicket = (TextView) findViewById(R.id.textViewNroTicket);
        textViewCategoria = (TextView) findViewById(R.id.textViewCategoria);
        textViewTituloCategoria = (TextView) findViewById(R.id.textViewTituloCategoria);
        textViewProblema = (TextView) findViewById(R.id.textViewProblema);
        textViewTituloProblema = (TextView) findViewById(R.id.textViewTituloProblema);
        textViewTituloDireccion = (TextView) findViewById(R.id.textViewTituloDireccion);
        textViewDireccion = (TextView) findViewById(R.id.textViewDireccion);
        progressBarLoad = (ProgressBar) findViewById(R.id.progressBarLoad);
        buttonAtender = (Button) findViewById(R.id.buttonAtender);
        buttonEmpezar = (Button) findViewById(R.id.buttonEmpezar);
        buttonFinalizar = (Button) findViewById(R.id.buttonFinalizar);
        buttonCancelar = (Button) findViewById(R.id.buttonCancelar);
        textViewTituloSolucion = (TextView) findViewById(R.id.textViewSolucion);
        spinnerSolucion = (Spinner) findViewById(R.id.spinnerSolucion);
        editTextDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
        editTextNombreDescripcion = (EditText) findViewById(R.id.editTextNombreDescripcion);
        editTextDescripcionProblema = (EditText) findViewById(R.id.editTextDescripcionProblema);

    }

    private class Cargar extends AsyncTask<Void, Void, Void> {

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("ticketId");
        String nombre = bundle.getString("nombre");

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Services(id, nombre);
            BeforeShow();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
                publishProgress();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            CompleteShow(Util.getHoraProcesor(prefs), Util.getHoraSolucion(prefs));
        }


    }

    private void BeforeShow() {
        progressBarLoad.setVisibility(View.VISIBLE);
        textViewNroTicketTitulo.setVisibility(View.INVISIBLE);
        textViewNroTicket.setVisibility(View.INVISIBLE);
        textViewCategoria.setVisibility(View.INVISIBLE);
        textViewTituloCategoria.setVisibility(View.INVISIBLE);
        textViewProblema.setVisibility(View.INVISIBLE);
        textViewTituloProblema.setVisibility(View.INVISIBLE);
        buttonAtender.setVisibility(View.INVISIBLE);
        buttonEmpezar.setVisibility(View.INVISIBLE);
        buttonFinalizar.setVisibility(View.INVISIBLE);
        buttonCancelar.setVisibility(View.INVISIBLE);
        textViewTituloSolucion.setVisibility(View.INVISIBLE);
        spinnerSolucion.setVisibility(View.INVISIBLE);
        editTextDescripcion.setVisibility(View.INVISIBLE);
        editTextNombreDescripcion.setVisibility(View.INVISIBLE);
        editTextDescripcionProblema.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);


        textViewTituloDireccion.setVisibility(View.INVISIBLE);
        textViewDireccion.setVisibility(View.INVISIBLE);
    }

    private void CompleteShow(String fecha, String fecha2) {
        progressBarLoad.setVisibility(View.INVISIBLE);
        textViewNroTicketTitulo.setVisibility(View.VISIBLE);
        textViewNroTicket.setVisibility(View.VISIBLE);
        textViewCategoria.setVisibility(View.VISIBLE);
        textViewTituloCategoria.setVisibility(View.VISIBLE);
        textViewProblema.setVisibility(View.VISIBLE);
        textViewTituloProblema.setVisibility(View.VISIBLE);
        textViewTituloSolucion.setVisibility(View.VISIBLE);
        spinnerSolucion.setVisibility(View.VISIBLE);
        editTextDescripcion.setVisibility(View.VISIBLE);
        editTextNombreDescripcion.setVisibility(View.VISIBLE);
        editTextDescripcionProblema.setVisibility(View.VISIBLE);

        textViewTituloDireccion.setVisibility(View.VISIBLE);
        textViewDireccion.setVisibility(View.VISIBLE);

        spinnerSolucion.setEnabled(false);
        editTextDescripcion.setEnabled(false);
        editTextNombreDescripcion.setEnabled(false);
        editTextDescripcionProblema.setEnabled(false);
        recyclerView.setVisibility(View.VISIBLE);

        if (fecha == null || fecha == "") {
            buttonAtender.setVisibility(View.VISIBLE);
        } else if (fecha2 == "" || fecha2 == null) {
            buttonAtender.setVisibility(View.INVISIBLE);
            buttonEmpezar.setVisibility(View.VISIBLE);
            buttonCancelar.setVisibility(View.VISIBLE);
            buttonFinalizar.setVisibility(View.INVISIBLE);

        } else {
            spinnerSolucion.setEnabled(true);
            editTextDescripcion.setEnabled(true);
            editTextNombreDescripcion.setEnabled(true);
            editTextDescripcionProblema.setEnabled(true);


            buttonAtender.setVisibility(View.INVISIBLE);
            buttonEmpezar.setVisibility(View.INVISIBLE);
            buttonCancelar.setVisibility(View.VISIBLE);
            buttonFinalizar.setVisibility(View.VISIBLE);
        }
    }


    private void Services(int id, final String titulo) {

        detalleTicketCall = ticketServices.getDetalleTicket(API.actionDetalleTicket, id);
        detalleTicketCall.enqueue(new Callback<DetalleTicket>() {
            @Override
            public void onResponse(Call<DetalleTicket> call, Response<DetalleTicket> response) {
                DetalleTicket detalleTicket = response.body();
                if (detalleTicket.getEstado().equals("E")) {
                    if (Util.getHoraProcesor(prefs).equals("")) {
                        showInfoAlert();
                    } else {
                        SetDatos(detalleTicket, titulo);
                    }
                } else {
                    SetDatos(detalleTicket, titulo);
                }

            }

            @Override
            public void onFailure(Call<DetalleTicket> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void SetDatos(DetalleTicket detalleTicket, String titulo) {
        textViewNroTicket.setText(detalleTicket.getNroTicket());
        textViewCategoria.setText(detalleTicket.getProblema().getDescripcion());
        textViewProblema.setText(detalleTicket.getProblema().getCategoria().getNombre());
        textViewDireccion.setText(detalleTicket.getDireccion());
        final int id = detalleTicket.getTicketId();
        btnAtender(id, titulo);
        btnCancelar(id);
        btnEmpezar(id, titulo);
        List<Solucion> solucions = detalleTicket.getSolucions();
        SolucionAdapter solucionAdapter = new SolucionAdapter(getBaseContext(), R.layout.solucion_option, solucions);
        spinnerSolucion.setAdapter(solucionAdapter);
        spinnerSolucion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String solucionId = ((TextView) view.findViewById(R.id.lblSolucionId)).getText().toString();
                String nombre = ((TextView) view.findViewById(R.id.lblSolucionName)).getText().toString();
                String descripcion = ((TextView) view.findViewById(R.id.lblSolucionDesc)).getText().toString();
                if (solucionId.equals("0")) {
                    editTextDescripcion.setText("");
                    editTextNombreDescripcion.setText("");
                    editTextDescripcion.setEnabled(true);
                    editTextNombreDescripcion.setEnabled(true);
                } else {
                    editTextDescripcion.setEnabled(false);
                    editTextNombreDescripcion.setEnabled(false);
                    editTextDescripcion.setText(nombre);
                    editTextNombreDescripcion.setText(descripcion);
                }

                btnFinalizar(id, solucionId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                editTextDescripcion.setVisibility(View.INVISIBLE);
                editTextNombreDescripcion.setVisibility(View.INVISIBLE);
            }
        });

        if (detalleTicket.getTicketDetalle() != null) {
            RAdapter = new TicketDetalleAdapter(detalleTicket.getTicketDetalle(), R.layout.lista_detalle, new TicketDetalleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(TicketDetalle listaTicket, int position) {
                    showMensaje(listaTicket);
                }
            });
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(RAdapter);
        }

    }

    private void btnAtender(final int id, final String titulo) {
        buttonAtender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atenderCall = ticketServices.saveAtender(API.actionSaveAtender, id);
                atenderCall.enqueue(new Callback<TicketDetalle>() {
                    @Override
                    public void onResponse(Call<TicketDetalle> call, Response<TicketDetalle> response) {
                        TicketDetalle fecha = response.body();
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("fechaHoraProceso", fecha.getFechaProceso());
                        editor.putInt("ticketId", id);
                        editor.putString("nombre", titulo);
                        editor.apply();
                        CompleteShow(Util.getHoraProcesor(prefs), Util.getHoraSolucion(prefs));
                    }

                    @Override
                    public void onFailure(Call<TicketDetalle> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "Error3", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void btnEmpezar(final int id, final String titulo) {
        buttonEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atenderCall = ticketServices.getEmpezar(API.actionSaveEmpezar);
                atenderCall.enqueue(new Callback<TicketDetalle>() {
                    @Override
                    public void onResponse(Call<TicketDetalle> call, Response<TicketDetalle> response) {
                        TicketDetalle fecha2 = response.body();
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("fechaHoraSolucion", fecha2.getFechaSolucion());
                        editor.apply();
                    /*    Intent intent = new Intent(getBaseContext(), TicketDetalleActivity.class);
                        intent.putExtra("ticketId", id);
                        intent.putExtra("nombre", titulo);
                        finish();
                        startActivity(intent);*/

                        CompleteShow(Util.getHoraProcesor(prefs), Util.getHoraSolucion(prefs));

                    }

                    @Override
                    public void onFailure(Call<TicketDetalle> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void btnCancelar(final int id) {
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atenderCall = ticketServices.getCancelar(API.actionCancelar, id);
                atenderCall.enqueue(new Callback<TicketDetalle>() {
                    @Override
                    public void onResponse(Call<TicketDetalle> call, Response<TicketDetalle> response) {
                        TicketDetalle fecha = response.body();
                        prefs.edit().clear().apply();
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        finish();
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<TicketDetalle> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void btnFinalizar(final int id, final String solucionId) {

        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = editTextDescripcion.getText().toString();
                String descripcion = editTextNombreDescripcion.getText().toString();
                String comentario = editTextDescripcionProblema.getText().toString();

                if (titulo.equals("") || titulo.equals(null)) {
                    editTextDescripcion.setError("Ingrese Titulo.");
                    editTextDescripcion.requestFocus();
                } else {
                    if (descripcion.equals("") || descripcion.equals(null)) {
                        editTextNombreDescripcion.setError("Ingrese Descripcion.");
                        editTextNombreDescripcion.requestFocus();
                    } else {
                        if (comentario.equals("") || comentario.equals(null)) {
                            editTextDescripcionProblema.setError("Escribir un Comentario.");
                            editTextDescripcionProblema.requestFocus();
                        } else {
                            atenderCall = ticketServices.getFinalizar(API.actionFinalizar, id, Util.getIdPrefs(usuario), Util.getHoraProcesor(prefs), Util.getHoraSolucion(prefs), comentario, Integer.parseInt(solucionId), titulo, descripcion);
                            atenderCall.enqueue(new Callback<TicketDetalle>() {
                                @Override
                                public void onResponse(Call<TicketDetalle> call, Response<TicketDetalle> response) {
                                    prefs.edit().clear().apply();
                                    TicketDetalle ticketDetalle = response.body();
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    finish();
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<TicketDetalle> call, Throwable t) {
                                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                    }
                }
            }
        });
    }

    private void showInfoAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        builder.setTitle("Mensaje");
        builder.setMessage("Este solicitud ya esta haciendo atendido. Gracias");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(TicketDetalleActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void showMensaje(TicketDetalle listaTicket) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        builder.setTitle(listaTicket.getTecnico());
        builder.setMessage(listaTicket.getDescripcion());
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


}

