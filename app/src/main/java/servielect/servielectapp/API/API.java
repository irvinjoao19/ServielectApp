package servielect.servielectapp.API;


import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import servielect.servielectapp.API.Deserializers.LoginDeserialize;
import servielect.servielectapp.Models.Usuario;

public class API {

    public static final String actionLogin = "loginMovil";
    public static final String actionSaveLocation = "SaveLocation";
    public static final String actionTicket = "ListAll";
    public static final String actionDetalleTicket = "ListOne";
    public static final String actionSaveAtender = "Atender";
    public static final String actionSaveEmpezar = "Empezar";
    public static final String actionCancelar = "Cancelar";
    public static final String actionFinalizar = "Finalizar";


    public static final String BASE_URL_LOGO = "http://servielectservicios.com/Electro/Library/img/";
    public static final String FORMATO = ".jpg";
    public static final String BASE_URL = "http://servielectservicios.com/Electro/Movil/";

    public static Retrofit retrofit = null;

    public static Retrofit getAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getApiLoginSerializer() {
        if (retrofit == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Usuario.class, new LoginDeserialize());
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }


}