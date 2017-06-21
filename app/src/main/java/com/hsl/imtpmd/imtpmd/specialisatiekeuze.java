package com.hsl.imtpmd.imtpmd;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hsl.imtpmd.imtpmd.model.UserModel;

public class specialisatiekeuze extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialisatiekeuze);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        View radiospec = findViewById(R.id.radiospec);




    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        Bundle bundle = getIntent().getExtras();
        UserModel user = UserModel.getUser(getApplicationContext(), bundle.getString("user"));

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.bdamradiobutton:
                if (checked)
                    // spec 2
                    user.setSpecialisatie(getApplicationContext(), "2");
                    break;
            case R.id.fictradiobutton:
                if (checked)
                    // spec 1
                    user.setSpecialisatie(getApplicationContext(), "1");
                    break;
            case R.id.mtradiobutton:
                if (checked)
                    //spec 4
                    user.setSpecialisatie(getApplicationContext(), "4");
                    break;
            case R.id.seradiobutton:
                if (checked)
                    //spec 3
                    user.setSpecialisatie(getApplicationContext(), "3");
                    break;
        }
    }

    public void writeSpec(){

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
