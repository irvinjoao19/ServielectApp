package servielect.servielectapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by irvin on 21/8/2017.
 */

public class Sede {

    private int EmpresaSedeId;
    @SerializedName("Empresa")
    private Empresa empresa;


    public Sede() {
    }

    public Sede(int empresaSedeId, Empresa empresa) {
        EmpresaSedeId = empresaSedeId;
        this.empresa = empresa;
    }

    public int getEmpresaSedeId() {
        return EmpresaSedeId;
    }

    public void setEmpresaSedeId(int empresaSedeId) {
        EmpresaSedeId = empresaSedeId;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
