package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText usuario;
    TextView email;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        usuario = findViewById(R.id.txtSearchUser);
        email = findViewById(R.id.txtViewEmail);

        buscar = findViewById(R.id.btnSearch);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String findUsuario = usuario.getText().toString();

                if(findUsuario.equals("")) {
                    usuario.setError("¡No ingresó ningún usuario!");
                } else {
                    List<Usuario> users = Usuario.find(Usuario.class, "usuario='"+findUsuario+"'");

                    if(users.size()<=0){
                        Toast.makeText(getApplicationContext(), "¡El usuario no existe!", Toast.LENGTH_LONG).show();
                        email.setText("");
                    } else {
                        Usuario user = users.get(0);
                        email.setText(user.getEmail());

                        Toast.makeText(getApplicationContext(), "¡Hecho!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void toHomeActivity(View view) {
        Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void toEmail(View view) {
        String url = "https://accounts.google.com/";
        Uri link = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, link);
        startActivity(intent);
    }
}