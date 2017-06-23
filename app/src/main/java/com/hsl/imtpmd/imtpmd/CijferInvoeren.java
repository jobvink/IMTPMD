package com.hsl.imtpmd.imtpmd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.VerplichtvakModel;

public class CijferInvoeren extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cijfer_invoeren);

        TextView invoer_code = (TextView) findViewById(R.id.invoer_code);
        TextView invoer_naam = (TextView) findViewById(R.id.invoer_naam);

        Bundle bundle = getIntent().getExtras();
        String code = null;
        String naam = null;
        int cijfer;
        Boolean behaald;
        if(bundle != null){
            code    = bundle.getString("code");
            naam    = bundle.getString("naam");
            cijfer  = bundle.getInt("cijfer");
            behaald = bundle.getBoolean("behaald");
        }
        if (code!=null) {
            invoer_code.setText(code);
            invoer_naam.setText(naam);
        }

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);



        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
