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
 * Created by Job Vink on 19-6-2017.
 */

public class UserVerplichtvakModel implements Model {
    public UserModel user;
    public VerplichtvakModel verplichtvak;
    public int behaald;
    public double cijfer;

    public UserVerplichtvakModel(UserModel user, VerplichtvakModel verplichtvak, int behaald, double cijfer) {
        this.user = user;
        this.verplichtvak = verplichtvak;
        this.behaald = behaald;
        this.cijfer = cijfer;
    }

    public void store(Context context) {
        ContentValues cv = this.createContentValues();
        DatabaseHelper databaseHelper = DatabaseHelper.getHelper(context);
        databaseHelper.insert(DatabaseInfo.User_VerplichtvakTables.USER_VERPLICHTVAK, null, cv);
        Log.d("Opslaan van: ", this.verplichtvak.getNaam());
    }

    public static ArrayList<UserVerplichtvakModel> all(Context context, UserModel user) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        ArrayList<UserVerplichtvakModel> all = new ArrayList<UserVerplichtvakModel>();

        String [] where = new String[]{
                user.getId()
        };

        Cursor rs = dbHelper.query(
                DatabaseInfo.User_VerplichtvakTables.USER_VERPLICHTVAK,
                new String[]{"*"},
                DatabaseInfo.User_verplichtvakColumn.USER_ID + "=?",
                where,
                null, null, null
        );
        rs.moveToFirst();

        DatabaseUtils.dumpCursor(rs);

        do {
            String user_id = "";
            String verplichtevak_id = "";
            int behaald = 0;
            double cijfer = 0;
            try {
                user_id = rs.getString(rs.getColumnIndex(DatabaseInfo.User_verplichtvakColumn.USER_ID));
                verplichtevak_id = rs.getString(rs.getColumnIndex(DatabaseInfo.User_verplichtvakColumn.VERPLICHTVAK_ID));
                behaald = rs.getInt(rs.getColumnIndex(DatabaseInfo.User_verplichtvakColumn.BEHAALD));
                cijfer = rs.getDouble(rs.getColumnIndex(DatabaseInfo.User_verplichtvakColumn.CIJFER));
                all.add(new UserVerplichtvakModel(
                        UserModel.getUser(context, Integer.parseInt(user_id)),
                        VerplichtvakModel.get(context, verplichtevak_id),
                        behaald,
                        cijfer));
            } catch (Exception e) {
                Log.e("VerplichtvakError: ", e.toString());
            }
        } while (rs.moveToNext());

        rs.close();
        return all;
    }

    public static void setBehaald(Context context, UserModel user, VerplichtvakModel vak, boolean behaald) {
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
        cv.put(DatabaseInfo.User_verplichtvakColumn.BEHAALD, behaald ? 1 : 0);
        mSQLDB.update(DatabaseInfo.User_VerplichtvakTables.USER_VERPLICHTVAK, cv,"user_id=? AND verplichtvak_id=?", new String[]{user.getId(), vak.getId()});
    }

    public static void setCijfer(Context context, UserModel user, String id, double cijfer) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_verplichtvakColumn.CIJFER, cijfer);
        mSQLDB.update(DatabaseInfo.User_VerplichtvakTables.USER_VERPLICHTVAK, cv,"user_id=? AND verplichtvak_id=?", new String[]{user.getId(), id});
        Log.d("Cijfer opslaan: ", Double.toString(cijfer));
    }

    public static ArrayList<UserVerplichtvakModel> propedeuze(Context context, UserModel user) {

        ArrayList<UserVerplichtvakModel> verplichtvakModels = all(context, user);

        ArrayList<UserVerplichtvakModel> propedeuzevakken = new ArrayList<UserVerplichtvakModel>();

        for (UserVerplichtvakModel vak : verplichtvakModels) {
            if (vak.getVerplichtvak().getJaar_id().equals("1")){
                propedeuzevakken.add(vak);
            }
        }

        return propedeuzevakken;
    }

    public static ArrayList<UserVerplichtvakModel> hoofdfase1(Context context, UserModel user) {

        ArrayList<UserVerplichtvakModel> verplichtvakModels = all(context, user);

        ArrayList<UserVerplichtvakModel> hooftfase1vakken = new ArrayList<UserVerplichtvakModel>();

        for (UserVerplichtvakModel vak : verplichtvakModels) {
            if (vak.getVerplichtvak().getJaar_id().equals("2")){
                hooftfase1vakken.add(vak);
            }
        }

        return hooftfase1vakken;
    }

    public static ArrayList<UserVerplichtvakModel> hoofdfase34(Context context, UserModel user) {

        ArrayList<UserVerplichtvakModel> verplichtvakModels = all(context, user);

        ArrayList<UserVerplichtvakModel> hooftfase1vakken = new ArrayList<UserVerplichtvakModel>();

        for (UserVerplichtvakModel vak : verplichtvakModels) {
            if (vak.getVerplichtvak().getJaar_id().equals("3")){
                hooftfase1vakken.add(vak);
            }
        }

        return hooftfase1vakken;
    }
    @Override
    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_verplichtvakColumn.USER_ID, this.user.getId());
        cv.put(DatabaseInfo.User_verplichtvakColumn.VERPLICHTVAK_ID, this.verplichtvak.getId());
        cv.put(DatabaseInfo.User_verplichtvakColumn.BEHAALD, this.behaald);
        cv.put(DatabaseInfo.User_verplichtvakColumn.CIJFER, this.cijfer);
        return cv;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public VerplichtvakModel getVerplichtvak() {
        return verplichtvak;
    }

    public void setVerplichtvak(VerplichtvakModel verplichtvak) {
        this.verplichtvak = verplichtvak;
    }

    public Boolean getBehaald() {
        return this.behaald == 1;
    }

    public double getCijfer() {
        return cijfer;
    }

    public String toString(){
        return this.verplichtvak.getCode();
    }
}
