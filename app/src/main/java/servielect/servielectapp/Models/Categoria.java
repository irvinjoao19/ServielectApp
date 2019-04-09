package servielect.servielectapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by irvin on 22/8/2017.
 */

public class Categoria {

    private int CategoriaId;
    @SerializedName("Nombre")
    private String Nombre;

    public Categoria() {
    }

    public Categoria(int categoriaId, String nombre) {
        CategoriaId = categoriaId;
        Nombre = nombre;
    }

    public int getCategoriaId() {
        return CategoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        CategoriaId = categoriaId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
