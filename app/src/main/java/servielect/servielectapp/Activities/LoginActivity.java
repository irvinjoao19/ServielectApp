package servielect.servielectapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import servielect.servielectapp.API.API;
import servielect.servielectapp.API.APIServices.UsuarioServices;
import servielect.servielectapp.Models.Usuario;
import servielect.servielectapp.R;
import servielect.servielectapp.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private UsuarioServices usuarioServices;
    private Call<Usuario> usuarioCall;

    private SharedPreferences prefs;

    private EditText Email;
    private EditText Password;
    private Button btnConsultar;
    private TelephonyManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        usuarioServices = API.getApiLoginSerializer().create(UsuarioServices.class);
        manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        // Consultamos
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                // Patterns.EMAIL_ADDRESS.matcher(email).matches() == false
                if (email == "" || email == null) {
                    Email.setError("Ingrese un correo.");
                    Email.requestFocus();
                } else {
                    if (password == "" || password == null) {
                        Password.setError("Ingrese una contraseña.");
                        Password.requestFocus();
                    } else {
                        if (password.length() < 3) {
                            Password.setError("La contraseña debe tener minimo 3 caracteres.");
                            Password.requestFocus();
                        } else {
                            goToServices(API.actionLogin, email, password);
                        }
                    }
                }
            }
        });
    }

    public void bindUI() {
        Email = (EditText) findViewById(R.id.editTextEmail);
        Password = (EditText) findViewById(R.id.editTextPassword);
        btnConsultar = (Button) findViewById(R.id.buttonEnviar);
    }
    private void saveOnPreferences(Usuario usuario) {
        try {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombre", usuario.getNombre());
            editor.putString("email", usuario.getEmail());
            editor.putInt("activo", usuario.getActivo());
            editor.putInt("nivel", usuario.getNivel());
            editor.putInt("id", usuario.getId());
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void goToServices(String action, String email, String password) {
        usuarioCall = usuarioServices.getLogin(action, email, password, Util.getMarca(),Util.getModelo(),Util.getVersionMovil(),Util.getCellId(getBaseContext()));
        usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario = response.body();
                if (usuario != null) {
                    saveOnPreferences(usuario);
                    goToMain();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o Contraseña incorrecta,Revise por favor!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error,Algo Inesperado Ocurrio", Toast.LENGTH_LONG).show();
            }
        });
    }
}

