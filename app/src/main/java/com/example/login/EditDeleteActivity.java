package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EditDeleteActivity extends AppCompatActivity {

    TextView email;
    EditText usuario, clave;
    Button actualizar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);

        email = findViewById(R.id.txtViewUserEmail);
        usuario = findViewById(R.id.txtEditUser);
        clave = findViewById(R.id.txtEditPassword);

        actualizar = findViewById(R.id.btnUpdate);
        eliminar = findViewById(R.id.btnDelete);

        Bundle bundle = getIntent().getExtras();
        String setUsuario = bundle.getString("UsuarioData");

        List<Usuario> users = Usuario.find(Usuario.class, "usuario='"+setUsuario+"'");
        Usuario user = users.get(0);

        String setEmail = user.getEmail() ;
        String setUser = user.getUsuario();
        String setPassword = user.getClave() ;

        email.setText(setEmail);
        usuario.setText(setUser);
        clave.setText(setPassword);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String findEmail = email.getText().toString();
                String updUsuario = usuario.getText().toString();
                String updClave = clave.getText().toString();

                if(updUsuario.equals("") && updClave.equals("")) {
                    usuario.setError("¡Por favor complete el campo usuario!");
                    clave.setError("¡Por favor complete el campo contraseña!");
                } else {
                    if (updUsuario.contains("@")) {
                        usuario.setError("¡El usuario no debe contener '@'!");
                    } else {
                        if (updUsuario.length() < 5 || updUsuario.length() > 10) {
                            usuario.setError("¡El usuario debe contener entre 5 a 10 caracteres!");
                        } else {
                            if (updClave.length() < 7) {
                                clave.setError("¡La contraseña debe contener mínimo 7 caracteres!");
                            } else {
                                List<Usuario> users = Usuario.find(Usuario.class, "email='"+findEmail+"'");
                                Usuario user = users.get(0);
                                String updUser = user.getUsuario();
                                String updPassword = user.getClave();

                                if(updUsuario.equals(updUser) && updClave.equals(updPassword)){
                                    Toast.makeText(getApplicationContext(), "¡No se encontraron cambios para actualizar", Toast.LENGTH_LONG).show();

                                } else {
                                    List<Usuario> usersDB = Usuario.find(Usuario.class, "usuario='"+updUsuario+"'");

                                    if (usersDB.size()<=0 || !updClave.equals(updPassword)) {
                                        user.setUsuario(updUsuario);
                                        user.setClave(updClave);
                                        user.save();
                                        usuario.setText(user.getUsuario());
                                        clave.setText(user.getClave());

                                        Toast.makeText(getApplicationContext(), "¡Información actualizada con éxito!", Toast.LENGTH_LONG).show();

                                        Intent intent = new Intent(EditDeleteActivity.this, HomeActivity.class);
                                        intent.putExtra("Usuario", updUsuario);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(getApplicationContext(), "¡El suario ya existe!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delUsuario = usuario.getText().toString();
                String delClave = clave.getText().toString();

                if(delUsuario.equals("") && delClave.equals("")) {
                    email.setText(setEmail);
                    usuario.setText(setUser);
                    clave.setText(setPassword);

                    Toast.makeText(getApplicationContext(), "¡Por favor no borre la información de los campos " +
                            "al eliminar un usuario!", Toast.LENGTH_LONG).show();
                } else {
                    List<Usuario> users = Usuario.find(Usuario.class, "usuario='"+delUsuario+"'");

                    if(users.size()<=0){
                        Toast.makeText(getApplicationContext(), "¡El usuario no existe!", Toast.LENGTH_LONG).show();
                    } else {
                        Usuario user = users.get(0);
                        String warning ="Usuario eliminado";
                        user.delete();

                        email.setText(warning);
                        usuario.setText("");
                        clave.setText("");

                        Toast.makeText(getApplicationContext(), "¡Usuario eliminado con éxito", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(EditDeleteActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    public void toHomeActivity2(View view) {
        Intent intent = new Intent(EditDeleteActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}