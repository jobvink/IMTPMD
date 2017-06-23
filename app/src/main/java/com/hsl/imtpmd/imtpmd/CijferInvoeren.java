package com.hsl.imtpmd.imtpmd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        double cijfer;
        final Boolean behaald;
        if(bundle != null){
            code    = bundle.getString("code");
            naam    = bundle.getString("naam");
            id      = bundle.getString("id");
            type    = bundle.getString("type");
            cijfer  = bundle.getDouble("cijfer");
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

        final EditText invoer_cijfer = (EditText) findViewById(R.id.invoer_cijfer);
        Button opslaanbutton = (Button) findViewById(R.id.opslaanbutton);

        final double[] behaalde_cijfer = {0};
        final UserModel user = UserModel.getUser(getApplicationContext(), getIntent().getExtras().getString("user"));

        final String finalType = type;
        final String finalId = id;
        opslaanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behaalde_cijfer[0] = Double.parseDouble(invoer_cijfer.getText().toString());
                Log.d("Behaalde cijfer: " , Double.toString(behaalde_cijfer[0]));
                if (behaalde_cijfer[0] >= 1 && behaalde_cijfer[0] <= 10) {
                    Log.d("type: ", finalType);
                    if (finalType != null && behaalde_cijfer[0] != 0) {
                        switch (finalType) {
                            case "v":
                                Log.d("het cijfer: ", Double.toString(behaalde_cijfer[0]));
                                UserVerplichtvakModel.setCijfer(getApplicationContext(), user, finalId, behaalde_cijfer[0]);
                                if (behaalde_cijfer[0] >= 5.5) {
                                    UserVerplichtvakModel.setBehaald(getApplicationContext(), user, finalId, true);
                                } else {
                                    UserVerplichtvakModel.setBehaald(getApplicationContext(), user, finalId, false);
                                }
                                break;
                            case "s":
                                UserSpecialisatievakModel.setCijfer(getApplicationContext(), user, finalId, behaalde_cijfer[0]);
                                if (behaalde_cijfer[0] >= 5.5) {
                                    UserSpecialisatievakModel.setBehaald(getApplicationContext(), user, finalId, true);
                                } else {
                                    UserSpecialisatievakModel.setBehaald(getApplicationContext(), user, finalId, false);
                                }
                                break;
                            default:
                                UserKeuzevakModel.setCijfer(getApplicationContext(), user, finalId, behaalde_cijfer[0]);
                                if (behaalde_cijfer[0] >= 5.5) {
                                    UserKeuzevakModel.setBehaald(getApplicationContext(), user, finalId, true);
                                } else {
                                    UserKeuzevakModel.setBehaald(getApplicationContext(), user, finalId, false);
                                }
                                break;
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Cijfer is opgeslagen" ,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Voer een geldig cijfer in" ,Toast.LENGTH_SHORT).show();
                }
            }
        });




    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
