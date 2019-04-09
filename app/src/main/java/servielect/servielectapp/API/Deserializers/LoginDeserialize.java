package servielect.servielectapp.API.Deserializers;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import servielect.servielectapp.Models.Usuario;

public class LoginDeserialize implements JsonDeserializer<Usuario> {

    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        int id = json.getAsJsonObject().get("Usuario").getAsJsonObject().get("id").getAsInt();
        String email = json.getAsJsonObject().get("Usuario").getAsJsonObject().get("Email").getAsString();
        int nivel = json.getAsJsonObject().get("Usuario").getAsJsonObject().get("Nivel").getAsInt();
        String nombre = json.getAsJsonObject().get("Usuario").getAsJsonObject().get("Persona").getAsJsonObject().get("Nombre").getAsString();
        int activo = json.getAsJsonObject().get("Usuario").getAsJsonObject().get("Persona").getAsJsonObject().get("Activo").getAsInt();


        Usuario usuario = new Usuario(id, email, nivel, nombre, activo);


        return usuario;
    }
}