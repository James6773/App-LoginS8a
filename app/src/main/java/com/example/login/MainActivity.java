package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText usuario, clave;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.txtUser);
        clave = findViewById(R.id.txtPassword);

        ingresar = findViewById(R.id.btnSignIn);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valUsuario = usuario.getText().toString();
                String valClave = clave.getText().toString();

                if(valUsuario.equals("") && valClave.equals("")) {
                    usuario.setError("¡Por favor ingrese su usuario!");
                    clave.setError("¡Por favor ingrese su contraseña!");
                } else {
                    List<Usuario> users = Usuario.find(Usuario.class, "usuario='"+valUsuario+"'");

                    if(users.size()<=0){
                        Toast.makeText(getApplicationContext(), "¡Usuario incorrecto!", Toast.LENGTH_LONG).show();
                    } else {
                        Usuario user = users.get(0);
                        String password = user.getClave();

                        if(!valClave.matches(password)) {
                            Toast.makeText(getApplicationContext(), "¡Contraseña incorrecta!", Toast.LENGTH_LONG).show();
                        } else {
                            usuario.setText("");
                            clave.setText("");

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("Usuario", valUsuario);
                            startActivity(intent);

                        }
                    }
                }
            }
        });
    }

    public void toSignupActivity(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void toFacebook (View view) {
        String url = "https://www.facebook.com/";
        Uri link = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, link);
        startActivity(intent);
    }

    public void toGoogle (View view) {
        String url = "https://www.google.com/";
        Uri link = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, link);
        startActivity(intent);
    }
}
