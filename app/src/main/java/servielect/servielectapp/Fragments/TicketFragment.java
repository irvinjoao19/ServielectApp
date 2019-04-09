package servielect.servielectapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import servielect.servielectapp.API.API;
import servielect.servielectapp.API.APIServices.TicketServices;
import servielect.servielectapp.API.Adapters.TicketAdapter;
import servielect.servielectapp.Activities.TicketDetalleActivity;
import servielect.servielectapp.Models.Ticket;
import servielect.servielectapp.Models.Tickets;
import servielect.servielectapp.R;
import servielect.servielectapp.Utils.Util;


public class TicketFragment extends Fragment {


    private SharedPreferences prefs;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter RAdapter;
    private TicketServices ticketServices;
    private Call<Tickets> ticketCall;

    private Tickets tickets;

    public TicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        prefs = this.getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        ticketServices = API.getAPI().create(TicketServices.class);


        RefreshData refreshData = new RefreshData();
        refreshData.execute();


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mainframe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RefreshData refreshData = new RefreshData();
                refreshData.execute();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        return view;
    }


    private class RefreshData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
            ticketCall = ticketServices.getTicket(API.actionTicket, Util.getNivelPrefs(prefs));
            ticketCall.enqueue(new Callback<Tickets>() {
                @Override
                public void onResponse(Call<Tickets> call, Response<Tickets> response) {
                    tickets = response.body();
                    RAdapter = new TicketAdapter(tickets.getTicketList(), R.layout.lista, new TicketAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Ticket tickets, int position) {
                            clickTicket(tickets);
                        }
                    });
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(RAdapter);
                }

                @Override
                public void onFailure(Call<Tickets> call, Throwable t) {
                    Toast.makeText(getContext(), "DATOS VACIOS", Toast.LENGTH_LONG).show();
                }
            });
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        protected void onCancelled() {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void clickTicket(Ticket ticket) {
        Intent intent = new Intent(getContext(), TicketDetalleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("ticketId", ticket.getTicketId());
        intent.putExtra("nombre", ticket.getSede().getEmpresa().getRazonSocial());
        startActivity(intent);
    }
}
