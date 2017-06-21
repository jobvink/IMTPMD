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
 * Created by Job Vink on 21-6-2017.
 */

public class UserSpecialisatievakModel implements Model {
    public UserModel user;
    public SpecialisatievakModel specialisatievakModel;
    public int behaald;

    public UserSpecialisatievakModel(UserModel user, SpecialisatievakModel specialisatievakModel, int behaald) {
        this.user = user;
        this.specialisatievakModel = specialisatievakModel;
        this.behaald = behaald;
    }

    public void store(Context context) {
        ContentValues cv = this.createContentValues();
        DatabaseHelper databaseHelper = DatabaseHelper.getHelper(context);
        databaseHelper.insert(DatabaseInfo.User_specialisatievakTables.USER_SPECIALISATIEVAK, null, cv);
        Log.d("Opslaan van: ", this.specialisatievakModel.getNaam());
    }

    public static ArrayList<UserSpecialisatievakModel> all(Context context, UserModel user) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        ArrayList<UserSpecialisatievakModel> all = new ArrayList<UserSpecialisatievakModel>();

        String [] where = new String[]{
                user.getId()
        };

        Cursor rs = dbHelper.query(
                DatabaseInfo.User_specialisatievakTables.USER_SPECIALISATIEVAK,
                new String[]{"*"},
                DatabaseInfo.User_specialisateivakColumn.USER_ID + "=?",
                where,
                null, null, null
        );
        rs.moveToFirst();

        DatabaseUtils.dumpCursor(rs);

        do {
            String user_id = "";
            String specialisatievak_id = "";
            int behaald = 0;
            try {
                user_id = rs.getString(rs.getColumnIndex(DatabaseInfo.User_specialisateivakColumn.USER_ID));
                specialisatievak_id = rs.getString(rs.getColumnIndex(DatabaseInfo.User_specialisateivakColumn.SPECIALISATIEVAK_ID));
                behaald = rs.getInt(rs.getColumnIndex(DatabaseInfo.User_specialisateivakColumn.BEHAALD));
                all.add(new UserSpecialisatievakModel(UserModel.getUser(context, Integer.parseInt(user_id)),SpecialisatievakModel.get(context, specialisatievak_id),behaald));
            } catch (Exception e) {
                Log.e("VerplichtvakError: ", e.toString());
            }
        } while (rs.moveToNext());

        return all;
    }

    public static void setBehaald(Context context, UserModel user, VerplichtvakModel verplichtvak) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_specialisateivakColumn.BEHAALD, 1);
        mSQLDB.update(DatabaseInfo.User_specialisatievakTables.USER_SPECIALISATIEVAK, cv,"user_id=? AND verplichtvak_id=?", new String[]{user.getId(), verplichtvak.getId()});
    }

    public static ArrayList<UserSpecialisatievakModel> propedeuze(Context context, UserModel user) {

        ArrayList<UserSpecialisatievakModel> verplichtvakModels = all(context, user);

        ArrayList<UserSpecialisatievakModel> propedeuzevakken = new ArrayList<UserSpecialisatievakModel>();

        for (UserSpecialisatievakModel vak : verplichtvakModels) {
            if (vak.getSpecialisatievakModel().getJaar_id().equals("1")){
                propedeuzevakken.add(vak);
            }
        }

        return propedeuzevakken;
    }

    public static ArrayList<UserSpecialisatievakModel> hoofdfase1(Context context, UserModel user) {

        ArrayList<UserSpecialisatievakModel> verplichtvakModels = all(context, user);

        ArrayList<UserSpecialisatievakModel> hooftfase1vakken = new ArrayList<UserSpecialisatievakModel>();

        for (UserSpecialisatievakModel vak : verplichtvakModels) {
            if (vak.getSpecialisatievakModel().getJaar_id().equals("1")){
                hooftfase1vakken.add(vak);
            }
        }

        return hooftfase1vakken;
    }

    @Override
    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_specialisateivakColumn.USER_ID, this.user.getId());
        cv.put(DatabaseInfo.User_specialisateivakColumn.SPECIALISATIEVAK_ID, this.specialisatievakModel.getId());
        cv.put(DatabaseInfo.User_specialisateivakColumn.BEHAALD, this.behaald);
        return cv;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public SpecialisatievakModel getSpecialisatievakModel() {
        return specialisatievakModel;
    }

    public void setSpecialisatievakModel(SpecialisatievakModel specialisatievakModel) {
        this.specialisatievakModel = specialisatievakModel;
    }

    public int getBehaald() {
        return behaald;
    }
}
