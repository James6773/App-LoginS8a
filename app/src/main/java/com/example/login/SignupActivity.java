package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SignupActivity extends AppCompatActivity {

    EditText email, usuario, clave, confClave;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.txtEmail);
        usuario = findViewById(R.id.txtEditUser);
        clave = findViewById(R.id.txtPassword2);
        confClave = findViewById(R.id.txtConfirmPassword);

        registrar = findViewById(R.id.btnSignUp);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int savDocumento = Integer.parseInt(savDocumento.getText().toString());

                String savEmail = email.getText().toString();
                String savUsuario = usuario.getText().toString();
                String savClave = clave.getText().toString();
                String savConfClave = confClave.getText().toString();


                if(savEmail.equals("") && savUsuario.equals("") && savClave.equals("") && savConfClave.equals("")) {
                    email.setError("¡Por favor complete el campo email!");
                    usuario.setError("¡Por favor complete el campo usuario!");
                    clave.setError("¡Por favor complete el campo contraseña!");
                    confClave.setError("¡Por favor complete el campo confirmar contraseña!");
                } else {
                    if (!savEmail.contains("@")) {
                        email.setError("¡El email debe contener '@'!");
                    } else {
                        if (savUsuario.contains("@")) {
                            usuario.setError("¡El usuario no debe contener '@'!");
                        } else {
                            if (savUsuario.length() < 5 || savUsuario.length() > 10) {
                                usuario.setError("¡El usuario debe contener entre 5 a 10 caracteres!");
                            } else {
                                if (savClave.length() < 7) {
                                    clave.setError("¡La contraseña debe contener mínimo 7 caracteres!");
                                } else {
                                    if (!savConfClave.matches(savClave)) {
                                        confClave.setError("¡Las contraseñas no coinciden!");
                                    } else {
                                        List<Usuario> users = Usuario.find(Usuario.class, "usuario='"+savUsuario+"'");

                                       if(users.size()<=0){
                                            Usuario newUser = new Usuario(savEmail, savUsuario, savClave, savConfClave);
                                            newUser.save();

                                            email.setText("");
                                            usuario.setText("");
                                            clave.setText("");
                                            confClave.setText("");

                                            Toast.makeText(getApplicationContext(), "¡Nuevo usuario registrado con éxito!", Toast.LENGTH_LONG).show();

                                        } else {
                                            Toast .makeText(getApplicationContext(), "¡El usuario ya existe!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void toMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}