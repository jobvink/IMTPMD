package com.hsl.imtpmd.imtpmd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.VerplichtvakModel;

public class CijferInvoeren extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cijfer_invoeren);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            Bundle bundle = getIntent().getExtras();
            String code;
            String naam;
            if(bundle != null){
                code = bundle.getString("code");
                naam = bundle.getString("naam");

            }


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
