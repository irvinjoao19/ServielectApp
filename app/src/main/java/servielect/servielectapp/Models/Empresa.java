package servielect.servielectapp.Models;

/**
 * Created by irvin on 21/8/2017.
 */

public class Empresa {

    private int EmpresaId;
    private String RazonSocial;

    public Empresa() {

    }

    public Empresa(int empresaId, String razonSocial) {
        EmpresaId = empresaId;
        RazonSocial = razonSocial;
    }

    public int getEmpresaId() {
        return EmpresaId;
    }

    public void setEmpresaId(int empresaId) {
        EmpresaId = empresaId;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }
}
