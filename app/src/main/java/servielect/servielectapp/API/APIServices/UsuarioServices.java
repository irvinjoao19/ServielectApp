package servielect.servielectapp.API.APIServices;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import servielect.servielectapp.Models.Ubicacion;
import servielect.servielectapp.Models.Usuario;

public interface UsuarioServices {
    @Headers("Cache-Control: no-cache")
    @GET("UsuarioController.php")
    Call<Usuario> getLogin(@Query("accionM") String action, @Query("usuario") String usu, @Query("password") String pass, @Query("marca") String marca, @Query("modelo") String modelo, @Query("android") String android, @Query("codigo") String imei);

    @Headers("Cache-Control: no-cache")
    @GET("CelularLocalizacionController.php")
    Call<Ubicacion> saveLocation(@Query("accionM") String action, @Query("codigo") String id, @Query("longitud") double longitud, @Query("latitud") double latitud);
}
