package com.hsl.imtpmd.imtpmd.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import com.hsl.imtpmd.imtpmd.database.DatabaseHelper;
import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;

import java.util.ArrayList;

import static com.hsl.imtpmd.imtpmd.database.DatabaseHelper.mSQLDB;

/**
 * Created by Job Vink on 22-6-2017.
 */

public class UserKeuzevakModel implements Model {
    public UserModel user;
    public KeuzevakModel keuzevak;
    public int behaald;
    public double cijfer;

    public UserKeuzevakModel(UserModel user, KeuzevakModel keuzevak, int behaald, double cijfer) {
        this.user = user;
        this.keuzevak = keuzevak;
        this.behaald = behaald;
        this.cijfer = cijfer;
    }

    public void store(Context context) {
        ContentValues cv = this.createContentValues();
        DatabaseHelper databaseHelper = DatabaseHelper.getHelper(context);
        databaseHelper.insert(DatabaseInfo.User_keuzevakTables.USER_KEUZEVAK, null, cv);
        Log.d("Opslaan van: ", this.keuzevak.getNaam());
    }

    public static ArrayList<UserKeuzevakModel> all(Context context, UserModel user) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        ArrayList<UserKeuzevakModel> all = new ArrayList<UserKeuzevakModel>();

        String [] where = new String[]{
                user.getId()
        };

        Cursor rs = dbHelper.query(
                DatabaseInfo.User_keuzevakTables.USER_KEUZEVAK,
                new String[]{"*"},
                DatabaseInfo.User_keuzevakColumn.USER_ID + "=?",
                where,
                null, null, null
        );
        rs.moveToFirst();

        Log.d("Ukv cursor: ", " Beter is hij gevuld");

        DatabaseUtils.dumpCursor(rs);

        do {
            String user_id = "";
            String verplichtevak_id = "";
            int behaald = 0;
            double cijfer = 0;
            try {
                user_id = rs.getString(rs.getColumnIndex(DatabaseInfo.User_keuzevakColumn.USER_ID));
                verplichtevak_id = rs.getString(rs.getColumnIndex(DatabaseInfo.User_keuzevakColumn.KEUZEVAK_ID));
                behaald = rs.getInt(rs.getColumnIndex(DatabaseInfo.User_keuzevakColumn.BEHAALD));
                cijfer = rs.getDouble(rs.getColumnIndex(DatabaseInfo.User_keuzevakColumn.CIJFER));
                all.add(new UserKeuzevakModel(
                        UserModel.getUser(context,
                                Integer.parseInt(user_id)),
                        KeuzevakModel.get(context, verplichtevak_id),
                        behaald,
                        cijfer));
            } catch (Exception e) {
                Log.e("Keuzevakerror: ", e.toString());
            }
        } while (rs.moveToNext());
        rs.close();
        return all;
    }

    public static void setBehaald(Context context, UserModel user, String id, Boolean behaald) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_keuzevakColumn.BEHAALD, behaald ? 1 : 0);
        mSQLDB.update(DatabaseInfo.User_keuzevakTables.USER_KEUZEVAK, cv,"user_id=? AND "+DatabaseInfo.User_keuzevakColumn.KEUZEVAK_ID+"=?", new String[]{user.getId(), id});
    }

    public static void setCijfer(Context context, UserModel user, String id, double cijfer){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_keuzevakColumn.CIJFER, cijfer);
        mSQLDB.update(DatabaseInfo.User_keuzevakTables.USER_KEUZEVAK, cv,"user_id=? AND "+DatabaseInfo.User_keuzevakColumn.KEUZEVAK_ID+"=?", new String[]{user.getId(), id});
    }

    @Override
    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_keuzevakColumn.USER_ID, this.user.getId());
        cv.put(DatabaseInfo.User_keuzevakColumn.KEUZEVAK_ID, this.keuzevak.getId());
        cv.put(DatabaseInfo.User_keuzevakColumn.BEHAALD, this.behaald);
        cv.put(DatabaseInfo.User_keuzevakColumn.CIJFER, this.cijfer);
        return cv;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public KeuzevakModel getKeuzevak() {
        return keuzevak;
    }

    public void setKeuzevak(KeuzevakModel keuzevak) {
        this.keuzevak = keuzevak;
    }

    public Boolean getBehaald() {
        return this.behaald == 1;
    }

    public double getCijfer() {
        return cijfer;
    }

    public String toString(){
        return this.keuzevak.getCode();
    }
}
