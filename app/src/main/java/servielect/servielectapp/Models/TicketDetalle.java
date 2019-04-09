package servielect.servielectapp.Models;


import com.google.gson.annotations.SerializedName;

public class TicketDetalle {

    @SerializedName("UsuarioId")
    private int UsuarioId;
    @SerializedName("tecnico")
    private String Tecnico;
    @SerializedName("FechaHoraProceso")
    private String FechaProceso;
    @SerializedName("FechaHoraSolucion")
    private String FechaSolucion;
    @SerializedName("FechaHoraResuelto")
    private String FechaResuelto;
    @SerializedName("DescripcionProblema")
    private String Descripcion;

    public TicketDetalle() {
    }

    public TicketDetalle(int usuarioId, String tecnico, String fechaProceso, String fechaSolucion, String fechaResuelto, String descripcion) {
        UsuarioId = usuarioId;
        Tecnico = tecnico;
        FechaProceso = fechaProceso;
        FechaSolucion = fechaSolucion;
        FechaResuelto = fechaResuelto;
        Descripcion = descripcion;
    }

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        UsuarioId = usuarioId;
    }

    public String getTecnico() {
        return Tecnico;
    }

    public void setTecnico(String tecnico) {
        Tecnico = tecnico;
    }

    public String getFechaProceso() {
        return FechaProceso;
    }

    public void setFechaProceso(String fechaProceso) {
        FechaProceso = fechaProceso;
    }

    public String getFechaSolucion() {
        return FechaSolucion;
    }

    public void setFechaSolucion(String fechaSolucion) {
        FechaSolucion = fechaSolucion;
    }

    public String getFechaResuelto() {
        return FechaResuelto;
    }

    public void setFechaResuelto(String fechaResuelto) {
        FechaResuelto = fechaResuelto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
