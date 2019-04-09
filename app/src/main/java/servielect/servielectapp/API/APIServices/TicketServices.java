package servielect.servielectapp.API.APIServices;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import servielect.servielectapp.Models.DetalleTicket;
import servielect.servielectapp.Models.TicketDetalle;
import servielect.servielectapp.Models.Tickets;


public interface TicketServices {
    @Headers("Cache-Control: no-cache")
    @GET("TicketController.php")
    Call<Tickets> getTicket(@Query("accionM") String action, @Query("nivel") int nivel);

    @Headers("Cache-Control: no-cache")
    @GET("TicketController.php")
    Call<DetalleTicket> getDetalleTicket(@Query("accionM") String action, @Query("ticketId") int ticketId);

    @Headers("Cache-Control: no-cache")
    @GET("TicketController.php")
    Call<TicketDetalle> saveAtender(@Query("accionM") String action, @Query("ticketId") int ticketId);

    @Headers("Cache-Control: no-cache")
    @GET("TicketController.php")
    Call<TicketDetalle> getEmpezar(@Query("accionM") String action);

    @Headers("Cache-Control: no-cache")
    @GET("TicketController.php")
    Call<TicketDetalle> getFinalizar(@Query("accionM") String action,
                                     @Query("ticketId") int ticketId,
                                     @Query("usuarioId") int usuarioId,
                                     @Query("fechaProceso") String fechaProceso,
                                     @Query("fechaSolucion") String fechaSolucion,
                                     @Query("descripcionProblema") String descripcionProblema,
                                     @Query("solucionId") int solucionId,
                                     @Query("nombreSolucion") String nombreSolucion,
                                     @Query("descripcionSolucion") String descripcionSolucion);

    @Headers("Cache-Control: no-cache")
    @GET("TicketController.php")
    Call<TicketDetalle> getCancelar(@Query("accionM") String action, @Query("ticketId") int ticketId);



}
