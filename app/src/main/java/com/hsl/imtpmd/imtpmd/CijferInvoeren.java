package com.hsl.imtpmd.imtpmd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hsl.imtpmd.imtpmd.model.UserKeuzevakModel;
import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.UserSpecialisatievakModel;
import com.hsl.imtpmd.imtpmd.model.UserVerplichtvakModel;
import com.hsl.imtpmd.imtpmd.model.VerplichtvakModel;

import java.util.Objects;

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
        String id = null;
        String type = null;
        int cijfer;
        Boolean behaald;
        if(bundle != null){
            code    = bundle.getString("code");
            naam    = bundle.getString("naam");
            id      = bundle.getString("id");
            type    = bundle.getString("type");
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

        EditText invoer_cijfer = (EditText) findViewById(R.id.invoer_cijfer);

        int behaalde_cijfer = Integer.parseInt(invoer_cijfer.getText().toString());

        UserModel user = UserModel.getUser(getApplicationContext(), getIntent().getExtras().getString("user"));

        if (type != null) {
            switch (type) {
                case "v":
                    UserVerplichtvakModel.setCijfer(getApplicationContext(), user, id, behaalde_cijfer);
                    if (behaalde_cijfer >= 5.5) {
                        UserVerplichtvakModel.setBehaald(getApplicationContext(), user, id, true);
                    } else {
                        UserVerplichtvakModel.setBehaald(getApplicationContext(), user, id, false);
                    }
                    break;
                case "s":
                    UserSpecialisatievakModel.setCijfer(getApplicationContext(), user, id, behaalde_cijfer);
                    if (behaalde_cijfer >= 5.5) {
                        UserSpecialisatievakModel.setBehaald(getApplicationContext(), user, id, true);
                    } else {
                        UserSpecialisatievakModel.setBehaald(getApplicationContext(), user, id, false);
                    }
                    break;
                default:
                    UserKeuzevakModel.setCijfer(getApplicationContext(), user, id, behaalde_cijfer);
                    if (behaalde_cijfer >= 5.5) {
                        UserKeuzevakModel.setBehaald(getApplicationContext(), user, id, true);
                    } else {
                        UserKeuzevakModel.setBehaald(getApplicationContext(), user, id, false);
                    }
                    break;
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
