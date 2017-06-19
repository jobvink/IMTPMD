package com.hsl.imtpmd.imtpmd.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import com.hsl.imtpmd.imtpmd.database.DatabaseHelper;
import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;

import java.util.ArrayList;

/**
 * Created by Job Vink on 19-6-2017.
 */

public class UserVerplichtvakModel implements Model {
    public UserModel user;
    public VerplichtvakModel verplichtvak;
    public int behaald;

    public UserVerplichtvakModel(UserModel user, VerplichtvakModel verplichtvak, int behaald) {
        this.user = user;
        this.verplichtvak = verplichtvak;
        this.behaald = behaald;
    }

    public void store(Context context) {
        ContentValues cv = this.createContentValues();
        DatabaseHelper databaseHelper = DatabaseHelper.getHelper(context);
        databaseHelper.insert(DatabaseInfo.User_VerplichtvakTables.User_Verplichtvak, null, cv);
        Log.d("Opslaan van: ", this.verplichtvak.getNaam());
    }

    public static ArrayList<VerplichtvakModel> all(Context context, UserModel user) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        ArrayList<VerplichtvakModel> all = new ArrayList<VerplichtvakModel>();

        Cursor rs = dbHelper.query(
                DatabaseInfo.VerplichtvakTables.VERPLICHTVAK,
                new String[]{"*"},
                null, null, null, null, null
        );
        rs.moveToFirst();

        do {
            String id = "";
            String code = "";
            String naam = "";
            String ec = "";
            String jaar_id = "";
            String periode = "";
            try {
                id = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.ID));
                code = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.CODE));
                naam = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.NAAM));
                ec = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.EC));
                jaar_id = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.JAAR_ID));
                periode = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.PERIODE));
                all.add(new VerplichtvakModel(id, code, naam, ec, jaar_id, periode));
            } catch (Exception e) {
                Log.e("Error: ", e.toString());
            }
        } while (rs.moveToNext());
        return all;
    }

    @Override
    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.User_verplichtvakColumn.USER_ID, this.user.getId());
        cv.put(DatabaseInfo.User_verplichtvakColumn.VERPLICHTVAK_ID, this.verplichtvak.getId());
        cv.put(DatabaseInfo.User_verplichtvakColumn.BEHAALD, this.behaald);
        return cv;
    }


}
