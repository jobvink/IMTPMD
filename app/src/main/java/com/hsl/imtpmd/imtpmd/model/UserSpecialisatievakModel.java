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
    public double cijfer;

    public UserSpecialisatievakModel(UserModel user, SpecialisatievakModel specialisatievakModel, int behaald, double cijfer) {
        this.user = user;
        this.specialisatievakModel = specialisatievakModel;
        this.behaald = behaald;
        this.cijfer = cijfer;
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
            double cijfer = 0;
            try {
                user_id = rs.getString(rs.getColumnIndex(DatabaseInfo.User_specialisateivakColumn.USER_ID));
                specialisatievak_id = rs.getString(rs.getColumnIndex(DatabaseInfo.User_specialisateivakColumn.SPECIALISATIEVAK_ID));
                behaald = rs.getInt(rs.getColumnIndex(DatabaseInfo.User_specialisateivakColumn.BEHAALD));
                cijfer = rs.getDouble(rs.getColumnIndex(DatabaseInfo.User_specialisateivakColumn.CIJFER));
                all.add(new UserSpecialisatievakModel(
                        UserModel.getUser(context, Integer.parseInt(user_id)),
                        SpecialisatievakModel.get(context, specialisatievak_id),
                        behaald,
                        cijfer));
            } catch (Exception e) {
                Log.e("VerplichtvakError: ", e.toString());
            }
        } while (rs.moveToNext());
        rs.close();
        return all;
    }

    public static void setBehaald(Context context, UserModel user, SpecialisatievakModel vak, boolean behaald) {
        if(behaald){
            if(vak.getJaar_id().equals("1")){
                user.addPEC(Integer.parseInt(vak.getEc()), context);
            } else {
                user.addHEC(Integer.parseInt(vak.getEc()), context);
            }
        } else {
            if(vak.getJaar_id().equals("1")){
                user.subPEC(Integer.parseInt(vak.getEc()), context);
            } else {
                user.subHEC(Integer.parseInt(vak.getEc()), context);
            }
        }
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_specialisateivakColumn.BEHAALD, behaald ? 1 : 0);
        mSQLDB.update(DatabaseInfo.User_specialisatievakTables.USER_SPECIALISATIEVAK, cv,"user_id=? AND " + DatabaseInfo.User_specialisateivakColumn.SPECIALISATIEVAK_ID + "=?", new String[]{user.getId(), vak.getId()});
    }

    public static void setCijfer(Context context, UserModel user, String id, double cijfer) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_specialisateivakColumn.CIJFER, cijfer);
        mSQLDB.update(DatabaseInfo.User_specialisatievakTables.USER_SPECIALISATIEVAK, cv,"user_id=? AND " + DatabaseInfo.User_specialisateivakColumn.SPECIALISATIEVAK_ID + "=?", new String[]{user.getId(), id});
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
            if (vak.getSpecialisatievakModel().getJaar_id().equals("2")){
                hooftfase1vakken.add(vak);
            }
        }

        return hooftfase1vakken;
    }

    public static ArrayList<UserSpecialisatievakModel> hoofdfase34(Context context, UserModel user) {

        ArrayList<UserSpecialisatievakModel> verplichtvakModels = all(context, user);

        ArrayList<UserSpecialisatievakModel> hooftfase1vakken = new ArrayList<UserSpecialisatievakModel>();

        for (UserSpecialisatievakModel vak : verplichtvakModels) {
            if (vak.getSpecialisatievakModel().getJaar_id().equals("3")){
                hooftfase1vakken.add(vak);
            }
        }

        return hooftfase1vakken;
    }

    public static ArrayList<UserSpecialisatievakModel> specialisatieFilter(ArrayList<UserSpecialisatievakModel> userSpecialisatievakModels, String specialisatie_id) {
        ArrayList<UserSpecialisatievakModel> filtered = new ArrayList<UserSpecialisatievakModel>();
        for (UserSpecialisatievakModel vak : userSpecialisatievakModels) {
            if (vak.getSpecialisatievakModel().getSpecialisatie_id().equals(specialisatie_id))
                filtered.add(vak);
        }
        return filtered;
    }

    @Override
    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_specialisateivakColumn.USER_ID, this.user.getId());
        cv.put(DatabaseInfo.User_specialisateivakColumn.SPECIALISATIEVAK_ID, this.specialisatievakModel.getId());
        cv.put(DatabaseInfo.User_specialisateivakColumn.BEHAALD, this.behaald);
        cv.put(DatabaseInfo.User_specialisateivakColumn.CIJFER, this.cijfer);
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

    public boolean getBehaald() {
        return this.behaald == 1;
    }

    public double getCijfer() {
        return cijfer;
    }

    public String toString(){
        return this.specialisatievakModel.getCode();
    }
}
