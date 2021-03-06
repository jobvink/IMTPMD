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

public class KeuzevakModel extends Vak implements Model{

    public KeuzevakModel(String id, String code, String naam, String ec) {
        super(id, code, naam, ec);
    }

    public static ArrayList<KeuzevakModel> all(Context context) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        ArrayList<KeuzevakModel> all = new ArrayList<KeuzevakModel>();

        Cursor rs = dbHelper.query(
                DatabaseInfo.KeuzevakTables.KEUZEVAK,
                new String[]{"*"},
                null, null, null, null, null
        );
        rs.moveToFirst();

        do {
            String id = "";
            String code = "";
            String naam = "";
            String ec = "";
            try {
                id = rs.getString(rs.getColumnIndex(DatabaseInfo.KeuzevakColumn.ID));
                code = rs.getString(rs.getColumnIndex(DatabaseInfo.KeuzevakColumn.CODE));
                naam = rs.getString(rs.getColumnIndex(DatabaseInfo.KeuzevakColumn.NAAM));
                ec = rs.getString(rs.getColumnIndex(DatabaseInfo.KeuzevakColumn.EC));
                all.add(new KeuzevakModel(id, code, naam, ec));
            } catch (Exception e) {
                Log.e("KeuzevakkenError: ", e.toString());
            }
        } while (rs.moveToNext());

        rs.close();
        return all;
    }

    public static KeuzevakModel get(Context context, String keuzevak_id){
        DatabaseHelper databaseHelper = DatabaseHelper.getHelper(context);

        String [] where = new String[]{
                keuzevak_id
        };

        Cursor rs = databaseHelper.query(
                DatabaseInfo.KeuzevakTables.KEUZEVAK,
                new String[]{"*"},
                DatabaseInfo.KeuzevakColumn.ID + "=?",
                where,
                null, null, null
        );
        rs.moveToFirst();

        String id = "";
        String code = "";
        String naam = "";
        String ec = "";
        KeuzevakModel keuzevakModel = null;
        try {
            id = rs.getString(rs.getColumnIndex(DatabaseInfo.KeuzevakColumn.ID));
            code = rs.getString(rs.getColumnIndex(DatabaseInfo.KeuzevakColumn.CODE));
            naam = rs.getString(rs.getColumnIndex(DatabaseInfo.KeuzevakColumn.NAAM));
            ec = rs.getString(rs.getColumnIndex(DatabaseInfo.KeuzevakColumn.EC));
            keuzevakModel = new KeuzevakModel(id, code, naam, ec);
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }

        rs.close();
        return keuzevakModel;
    }


    @Override
    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.KeuzevakColumn.ID, this.id);
        cv.put(DatabaseInfo.KeuzevakColumn.CODE, this.code);
        cv.put(DatabaseInfo.KeuzevakColumn.NAAM, this.naam);
        cv.put(DatabaseInfo.KeuzevakColumn.EC, this.ec);
        return cv;
    }

    public String toString(){
        return this.code;
    }
}
