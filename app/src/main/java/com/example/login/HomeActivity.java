package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usuario = findViewById(R.id.txtViewUserName);

        Bundle bundle = getIntent().getExtras();

        String setUsuario = bundle.getString("Usuario");

        usuario.setText(setUsuario);
    }

    public void toSearchActivity(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void toSignUpActivity2(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void toEditDeleteActivity(View view) {
        Bundle bundle = getIntent().getExtras();

        String catchUsuario = bundle.getString("Usuario");

        Intent intent = new Intent(this, EditDeleteActivity.class);
        intent.putExtra("UsuarioData", catchUsuario);
        startActivity(intent);
    }

    public void toAboutActivity(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void toMainActivity2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}