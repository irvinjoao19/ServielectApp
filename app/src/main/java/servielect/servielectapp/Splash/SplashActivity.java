package servielect.servielectapp.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import servielect.servielectapp.Activities.LoginActivity;
import servielect.servielectapp.Activities.MainActivity;
import servielect.servielectapp.Activities.TicketDetalleActivity;
import servielect.servielectapp.Utils.Util;


public class SplashActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private SharedPreferences ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        ticket = getSharedPreferences("TicketPreferences", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);
        Intent intentTicket = new Intent(this, TicketDetalleActivity.class);
        if (Util.getTicketId(ticket) != 0) {
            intentTicket.putExtra("ticketId", Util.getTicketId(ticket));
            intentTicket.putExtra("nombre", Util.getNameTicket(ticket));
            startActivity(intentTicket);
        } else {
            if (Util.getNombrePrefs(prefs) != "") {
                startActivity(intentMain);
            } else {
                startActivity(intentLogin);
            }
        }
        finish();

    }
}
