package servielect.servielectapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Created by irvin on 8/7/2017.
 */

public class Util {

    public static String getNombrePrefs(SharedPreferences preferences) {
        return preferences.getString("nombre", "");
    }

    public static String getEmailPrefs(SharedPreferences preferences) {
        return preferences.getString("email", "");
    }

    public static int getActivoPrefs(SharedPreferences preferences) {
        return preferences.getInt("activo", 0);
    }

    public static int getNivelPrefs(SharedPreferences preferences) {
        return preferences.getInt("nivel", 0);
    }

    public static int getIdPrefs(SharedPreferences preferences) {
        return preferences.getInt("id", 0);
    }

    public static String getCellId(Context context) {
       String imei = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return imei;
    }

    public static String getNumber(TelephonyManager manager) {

        String telefono = manager.getLine1Number();
        String valor = null;
        if (telefono == null){
            valor = "965308371";
        }else{
            valor = telefono;
        }
        return valor;
    }

    public static String getMarca() {
        return Build.BRAND;
    }

    public static String getModelo() {
        return Build.MODEL;
    }

    public static String getVersionMovil() {
        return Build.VERSION.RELEASE;
    }



    public static String getHoraProcesor(SharedPreferences preferences){
        return preferences.getString("fechaHoraProceso", "");
    }
    public static String getHoraSolucion(SharedPreferences preferences){
        return preferences.getString("fechaHoraSolucion", "");
    }

    public static int getTicketId(SharedPreferences preferences){
        return preferences.getInt("ticketId", 0);
    }
    public static String getNameTicket(SharedPreferences preferences){
        return preferences.getString("nombre", "");
    }



}
