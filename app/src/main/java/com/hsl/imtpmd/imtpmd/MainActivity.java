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

import com.hsl.imtpmd.imtpmd.database.DatabaseHelper;
import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper databaseHelper = DatabaseHelper.getHelper(this);

        final Button inloggen = (Button) findViewById(R.id.inloggen);
        final EditText gebruikersnaam_veld = (EditText) findViewById(R.id.gebruikersnaam);
        final EditText wachtwoord_veld = (EditText) findViewById(R.id.wachtwoord);

        inloggen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] where = new String[]{
                        gebruikersnaam_veld.getText().toString()
                };

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
                    Intent intent = new Intent(MainActivity.this, HoofdschermActivity.class);
                    Toast.makeText(getApplicationContext(), "Inloggen succesvol", Toast.LENGTH_SHORT);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wachtwoord en gebruikersnaam komen niet overeen" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
