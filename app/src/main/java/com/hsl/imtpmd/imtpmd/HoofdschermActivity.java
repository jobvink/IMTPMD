package com.hsl.imtpmd.imtpmd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.hsl.imtpmd.imtpmd.model.UserModel;

public class HoofdschermActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoofdscherm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Bundle bundle = getIntent().getExtras();
        UserModel user;
        if (bundle != null)
            user = UserModel.getUser(getApplicationContext(), bundle.getString("user"));

        Button propedeuseButton = (Button) findViewById(R.id.propedeuseButton);
        Button hoofdfase1Button = (Button) findViewById(R.id.hoofdfase1Button);
        Button hoofdfase34Button = (Button) findViewById(R.id.hoofdfase34Button);
        Button puntenOverzichtbutton = (Button) findViewById(R.id.puntenOverzichtbutton);
        Button Instellingenbutton = (Button) findViewById(R.id.Instellingenbutton);

        propedeuseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoofdschermActivity.this, Propedeuse.class).putExtras(bundle));
            }
        });

        hoofdfase1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoofdschermActivity.this, Hoofdfase1.class).putExtras(bundle));
            }
        });

        hoofdfase34Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoofdschermActivity.this, Hoofdfase34.class).putExtras(bundle));
            }
        });

        puntenOverzichtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoofdschermActivity.this, PuntenOverzicht.class).putExtras(bundle));
            }
        });

        Instellingenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoofdschermActivity.this, specialisatiekeuze.class).putExtras(bundle));
            }
        });

    }
}
