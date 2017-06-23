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

    public UserModel(String id, String gebruikersnaam, String wachtwoord, String specialisatie) {
        this.id = id;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.specialisatie = specialisatie;
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

        try {
            id = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.ID));
            dbgebruikersnaam = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.GEBRUIKERSNAAM));
            wachtwoord = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.WACHTWOORD));
            specialisatie = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.SPECIALISATIE));
            if (specialisatie.equals("null")) specialisatie = null;
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }
        rs.close();
        return new UserModel(id, dbgebruikersnaam, wachtwoord, specialisatie);
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

        try {
            id = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.ID));
            dbgebruikersnaam = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.GEBRUIKERSNAAM));
            wachtwoord = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.WACHTWOORD));
            specialisatie = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.SPECIALISATIE));
            if (specialisatie.equals("null")) specialisatie = null;
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }

        return new UserModel(id, dbgebruikersnaam, wachtwoord, specialisatie);
    }
    @Override
    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.UserColumn.ID, this.id);
        cv.put(DatabaseInfo.UserColumn.GEBRUIKERSNAAM, this.gebruikersnaam);
        cv.put(DatabaseInfo.UserColumn.WACHTWOORD, this.wachtwoord);
        cv.put(DatabaseInfo.UserColumn.SPECIALISATIE, this.specialisatie);
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

    public int getPropedeuzePunten(Context context){
        int ec = 0;

        ArrayList<UserVerplichtvakModel> userVerplichtvakModels = UserVerplichtvakModel.propedeuze(context, this);
        ArrayList<UserSpecialisatievakModel> userSpecialisatievakModels = UserSpecialisatievakModel.propedeuze(context, this);

        for (UserVerplichtvakModel vak : userVerplichtvakModels){
            if(vak.getBehaald()){
                ec += Integer.parseInt(vak.getVerplichtvak().getEc());
            }
        }

        for (UserSpecialisatievakModel vak : userSpecialisatievakModels){
            if(vak.getBehaald()){
                ec += Integer.parseInt(vak.getSpecialisatievakModel().getEc());
            }
        }

        return ec;
    }

    public int getBachalorPunten(Context context){

        int ec = 0;

        ArrayList<UserKeuzevakModel> keuzevakModels = UserKeuzevakModel.all(context, this);
        ArrayList<UserVerplichtvakModel> verplichtvakModels1 = UserVerplichtvakModel.hoofdfase1(context, this);
        ArrayList<UserVerplichtvakModel> verplichtvakModels2 = UserVerplichtvakModel.hoofdfase34(context, this);
        ArrayList<UserSpecialisatievakModel> specialisatievakModels1 = UserSpecialisatievakModel.hoofdfase1(context, this);
        ArrayList<UserSpecialisatievakModel> specialisatievakModels2 = UserSpecialisatievakModel.hoofdfase34(context, this);

        for (UserKeuzevakModel vak : keuzevakModels){
            if(vak.getBehaald()){
                ec += Integer.parseInt(vak.getKeuzevak().getEc());
            }
        }

        for (UserVerplichtvakModel vak : verplichtvakModels1){
            if(vak.getBehaald()){
                ec += Integer.parseInt(vak.getVerplichtvak().getEc());
            }
        }

        for (UserVerplichtvakModel vak : verplichtvakModels2){
            if(vak.getBehaald()){
                ec += Integer.parseInt(vak.getVerplichtvak().getEc());
            }
        }

        for (UserSpecialisatievakModel vak : specialisatievakModels1){
            if(vak.getBehaald()){
                ec += Integer.parseInt(vak.getSpecialisatievakModel().getEc());
            }
        }

        for (UserSpecialisatievakModel vak : specialisatievakModels1){
            if(vak.getBehaald()){
                ec += Integer.parseInt(vak.getSpecialisatievakModel().getEc());
            }
        }

        return ec;
    }
}
