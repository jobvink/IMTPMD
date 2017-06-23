package com.hsl.imtpmd.imtpmd.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.hsl.imtpmd.imtpmd.database.DatabaseHelper;
import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;

import java.util.ArrayList;

/**
 * Created by jobvink on 15-06-17.
 */

public class VerplichtvakModel extends Vak implements Model {
    public String jaar_id;
    public String periode;

    public VerplichtvakModel(String id, String code, String naam, String ec, String jaar_id, String periode) {
        super(id, code, naam, ec);
        this.jaar_id = jaar_id;
        this.periode = periode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }


    public String getJaar_id() {
        return jaar_id;
    }

    public void setJaar_id(String jaar_id) {
        this.jaar_id = jaar_id;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }


    public static ArrayList<VerplichtvakModel> all(Context context) {
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

        rs.close();
        return all;
    }

    public static VerplichtvakModel get(Context context, String verplichtvak_id){
        DatabaseHelper databaseHelper = DatabaseHelper.getHelper(context);

        String [] where = new String[]{
                verplichtvak_id
        };

        Cursor rs = databaseHelper.query(
                DatabaseInfo.VerplichtvakTables.VERPLICHTVAK,
                new String[]{"*"},
                DatabaseInfo.VerplichtvakColumn.ID + "=?",
                where,
                null, null, null
        );
        rs.moveToFirst();

        String id = "";
        String code = "";
        String naam = "";
        String ec = "";
        String jaar_id = "";
        String periode = "";
        VerplichtvakModel verplichtvakModel = null;
        try {
            id = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.ID));
            code = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.CODE));
            naam = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.NAAM));
            ec = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.EC));
            jaar_id = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.JAAR_ID));
            periode = rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.PERIODE));
            verplichtvakModel = new VerplichtvakModel(id, code, naam, ec, jaar_id, periode);
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }

        return verplichtvakModel;
    }

    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.VerplichtvakColumn.ID, this.id);
        cv.put(DatabaseInfo.VerplichtvakColumn.CODE, this.code);
        cv.put(DatabaseInfo.VerplichtvakColumn.NAAM, this.naam);
        cv.put(DatabaseInfo.VerplichtvakColumn.EC, this.ec);
        cv.put(DatabaseInfo.VerplichtvakColumn.JAAR_ID, this.jaar_id);
        cv.put(DatabaseInfo.VerplichtvakColumn.PERIODE, this.periode);
        return cv;
    }

    public String toString(){
        return this.code;
    }
}
