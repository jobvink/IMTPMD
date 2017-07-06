package com.hsl.imtpmd.imtpmd.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import com.hsl.imtpmd.imtpmd.database.DatabaseHelper;
import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;

import java.util.ArrayList;

/**
 * Created by jobvink on 15-06-17.
 */

public class UserModel implements Model {
    private String id;
    private String gebruikersnaam;
    private String wachtwoord;
    private String specialisatie;
    private int propedeuze_ec;
    private int hoofdfase_ec;

    public UserModel(String id, String gebruikersnaam, String wachtwoord, String specialisatie, int propedeuze_ec, int hoofdfase_ec) {
        this.id = id;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.specialisatie = specialisatie;
        this.propedeuze_ec = propedeuze_ec;
        this.hoofdfase_ec = hoofdfase_ec;
    }

    public static UserModel getUser(Context context, String gebruikersnaam) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        String [] where = new String[]{
                gebruikersnaam
        };

        Cursor rs = dbHelper.query(
                DatabaseInfo.UserTables.USER,
                new String[]{"*"},
                DatabaseInfo.UserColumn.GEBRUIKERSNAAM + "=?",
                where,
                null, null, null
        );
        rs.moveToFirst();

        DatabaseUtils.dumpCursor(rs);

        String id = "";
        String dbgebruikersnaam = "";
        String wachtwoord = "";
        String specialisatie = null;
        int propedeuze_ec = 0;
        int hoofdfase_ec = 0;

        try {
            id = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.ID));
            dbgebruikersnaam = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.GEBRUIKERSNAAM));
            wachtwoord = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.WACHTWOORD));
            specialisatie = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.SPECIALISATIE));
            propedeuze_ec = rs.getInt(rs.getColumnIndex(DatabaseInfo.UserColumn.PROPEDEUZE_EC));
            hoofdfase_ec = rs.getInt(rs.getColumnIndex(DatabaseInfo.UserColumn.HOOFDFASE_EC));
            if (specialisatie.equals("null")) specialisatie = null;
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }
        rs.close();
        return new UserModel(id, dbgebruikersnaam, wachtwoord, specialisatie, propedeuze_ec, hoofdfase_ec);
    }
    public static UserModel getUser(Context context, int user_id) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        String [] where = new String[]{
                Integer.toString(user_id)
        };

        Cursor rs = dbHelper.query(
                DatabaseInfo.UserTables.USER,
                new String[]{"*"},
                DatabaseInfo.UserColumn.ID + "=?",
                where,
                null, null, null
        );
        rs.moveToFirst();

        String id = "";
        String dbgebruikersnaam = "";
        String wachtwoord = "";
        String specialisatie = null;
        int propedeuze_ec = 0;
        int hoofdfase_ec = 0;

        try {
            id = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.ID));
            dbgebruikersnaam = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.GEBRUIKERSNAAM));
            wachtwoord = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.WACHTWOORD));
            specialisatie = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.SPECIALISATIE));
            propedeuze_ec = rs.getInt(rs.getColumnIndex(DatabaseInfo.UserColumn.PROPEDEUZE_EC));
            hoofdfase_ec = rs.getInt(rs.getColumnIndex(DatabaseInfo.UserColumn.HOOFDFASE_EC));
            if (specialisatie.equals("null")) specialisatie = null;
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }

        return new UserModel(id, dbgebruikersnaam, wachtwoord, specialisatie, propedeuze_ec, hoofdfase_ec);
    }
    @Override
    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.UserColumn.ID, this.id);
        cv.put(DatabaseInfo.UserColumn.GEBRUIKERSNAAM, this.gebruikersnaam);
        cv.put(DatabaseInfo.UserColumn.WACHTWOORD, this.wachtwoord);
        cv.put(DatabaseInfo.UserColumn.SPECIALISATIE, this.specialisatie);
        cv.put(DatabaseInfo.UserColumn.PROPEDEUZE_EC, this.propedeuze_ec);
        cv.put(DatabaseInfo.UserColumn.HOOFDFASE_EC, this.hoofdfase_ec);
        return cv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getSpecialisatie() {
        return specialisatie;
    }

    public void setSpecialisatie(Context context, String specialisatie) {
        this.specialisatie = specialisatie;
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
        dbHelper.update(DatabaseInfo.UserTables.USER,createContentValues(),getId());
    }

    public void addPEC(int ec, Context context) {
        this.propedeuze_ec += ec;
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
        dbHelper.update(DatabaseInfo.UserTables.USER,createContentValues(),getId());
    }

    public void subPEC(int ec, Context context) {
        this.propedeuze_ec -= ec;
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
        dbHelper.update(DatabaseInfo.UserTables.USER,createContentValues(),getId());
    }

    public void addHEC(int ec, Context context) {
        this.hoofdfase_ec += ec;
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
        dbHelper.update(DatabaseInfo.UserTables.USER,createContentValues(),getId());
    }

    public void subHEC(int ec, Context context) {
        this.hoofdfase_ec -= ec;
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
        dbHelper.update(DatabaseInfo.UserTables.USER,createContentValues(),getId());
    }

    public int getPropedeuze_ec() {
        return propedeuze_ec;
    }

    public int getHoofdfase_ec() {
        return hoofdfase_ec;
    }
}
