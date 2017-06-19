package com.hsl.imtpmd.imtpmd.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import com.hsl.imtpmd.imtpmd.database.DatabaseHelper;
import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;

/**
 * Created by jobvink on 15-06-17.
 */

public class UserModel implements Model {
    private String id;
    private String gebruikersnaam;
    private String wachtwoord;

    public UserModel(String id, String gebruikersnaam, String wachtwoord) {
        this.id = id;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
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

        try {
            id = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.ID));
            dbgebruikersnaam = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.GEBRUIKERSNAAM));
            wachtwoord = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.WACHTWOORD));
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }

        return new UserModel(id, dbgebruikersnaam, wachtwoord);
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

        try {
            id = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.ID));
            dbgebruikersnaam = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.GEBRUIKERSNAAM));
            wachtwoord = rs.getString(rs.getColumnIndex(DatabaseInfo.UserColumn.WACHTWOORD));
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }

        return new UserModel(id, dbgebruikersnaam, wachtwoord);
    }
    @Override
    public ContentValues createContentValues() {
        return null;
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
}
