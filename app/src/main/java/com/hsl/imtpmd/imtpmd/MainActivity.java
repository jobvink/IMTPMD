package com.hsl.imtpmd.imtpmd;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hsl.imtpmd.imtpmd.api.VakkenApi;
import com.hsl.imtpmd.imtpmd.database.DatabaseHelper;
import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;
import com.hsl.imtpmd.imtpmd.model.KeuzevakModel;
import com.hsl.imtpmd.imtpmd.model.UserKeuzevakModel;
import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.UserSpecialisatievakModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper databaseHelper = DatabaseHelper.getHelper(this);

        final Button inloggen = (Button) findViewById(R.id.inloggen);
        final EditText gebruikersnaam_veld = (EditText) findViewById(R.id.gebruikersnaam);
        final EditText wachtwoord_veld = (EditText) findViewById(R.id.wachtwoord);

        DatabaseHelper db = DatabaseHelper.getHelper(getApplicationContext());
        Cursor mCursor = db.query(DatabaseInfo.VerplichtvakTables.VERPLICHTVAK,new String[]{"*"},null,null,null,null,null);
        Boolean rowExists = mCursor.moveToFirst();

        if (!rowExists) {
            VakkenApi api = VakkenApi.getApi(getApplicationContext());
            api.requestVerplichtenVakken(getApplicationContext());
            api.requestSpecialisatievakken(getApplicationContext());
            api.requestKeuzevakken(getApplicationContext());
        }


        inloggen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] where = new String[]{
                        gebruikersnaam_veld.getText().toString()
                };

                VakkenApi api = VakkenApi.getApi(getApplicationContext());



                Cursor rs = databaseHelper.query(
                        DatabaseInfo.UserTables.USER,
                        new String[]{DatabaseInfo.UserColumn.GEBRUIKERSNAAM, DatabaseInfo.UserColumn.WACHTWOORD},
                        DatabaseInfo.UserColumn.GEBRUIKERSNAAM + "=?",
                        where,
                        null, null, null
                );
                rs.moveToFirst();
                DatabaseUtils.dumpCursor(rs);


                String wachtwoord = "";

                try {
                    wachtwoord = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.WACHTWOORD));
                } catch (Exception e) {
                    Log.e("Error: ", e.toString());
                }

                if(wachtwoord_veld.getText().toString().equals(wachtwoord)){
                    Intent intent = new Intent(MainActivity.this, OverzichtActivity.class);

                    UserModel user = UserModel.getUser(getApplicationContext(), gebruikersnaam_veld.getText().toString());

                    DatabaseHelper db = DatabaseHelper.getHelper(getApplicationContext());
                    Cursor mCursor = db.query(DatabaseInfo.User_VerplichtvakTables.USER_VERPLICHTVAK,new String[]{"*"},
                            DatabaseInfo.UserColumn.ID + "=?",
                            new String[]{user.getId()},null,null,null);
                    Boolean userSeeded = mCursor.moveToFirst();


                    if (!userSeeded) {
                        Log.d("Main: ", " Seeding user");
                        api.seedUser(getApplicationContext(), gebruikersnaam_veld.getText().toString());
                    }

                    ArrayList<UserKeuzevakModel> userKeuzevakModels = UserKeuzevakModel.all(getApplicationContext(), user);
                    for(UserKeuzevakModel vak : userKeuzevakModels) {
                        Log.d("Main: ",vak.getKeuzevak().getNaam());
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("user", user.getGebruikersnaam());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wachtwoord en gebruikersnaam komen niet overeen" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
