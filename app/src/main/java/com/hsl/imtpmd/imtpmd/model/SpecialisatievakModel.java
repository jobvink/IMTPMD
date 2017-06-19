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

public class SpecialisatievakModel extends Vak implements Model {
    public String specialisatie_id;

    public SpecialisatievakModel(String id, String code, String naam, String ec, String specialisatie_id) {
        super(id, code, naam, ec);
        this.specialisatie_id = specialisatie_id;
    }

    public String getSpecialisatie_id() {
        return specialisatie_id;
    }

    public void setSpecialisatie_id(String specialisatie_id) {
        this.specialisatie_id = specialisatie_id;
    }

    public static ArrayList<SpecialisatievakModel> all(Context context) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        ArrayList<SpecialisatievakModel> all = new ArrayList<SpecialisatievakModel>();

        Cursor rs = dbHelper.query(
                DatabaseInfo.UserTables.USER,
                new String[]{"*"},
                null, null, null, null, null
        );
        rs.moveToFirst();

        do {
            String id = "";
            String code = "";
            String naam = "";
            String ec = "";
            String specialisatie_id = "";
            try {
                id = rs.getString(rs.getColumnIndex(DatabaseInfo.SpecialisatievakColumn.ID));
                code = rs.getString(rs.getColumnIndex(DatabaseInfo.SpecialisatievakColumn.CODE));
                naam = rs.getString(rs.getColumnIndex(DatabaseInfo.SpecialisatievakColumn.NAAM));
                ec = rs.getString(rs.getColumnIndex(DatabaseInfo.SpecialisatievakColumn.EC));
                specialisatie_id = rs.getString(rs.getColumnIndex(DatabaseInfo.SpecialisatievakColumn.SPECIALISATIE_ID));
                all.add(new SpecialisatievakModel(id, code, naam, ec, specialisatie_id));
            } catch (Exception e) {
                Log.e("Error: ", e.toString());
            }
        } while (rs.moveToNext());

        return all;
    }

    @Override
    public ContentValues createContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.SpecialisatievakColumn.ID, this.id);
        cv.put(DatabaseInfo.SpecialisatievakColumn.CODE, this.code);
        cv.put(DatabaseInfo.SpecialisatievakColumn.NAAM, this.naam);
        cv.put(DatabaseInfo.SpecialisatievakColumn.EC, this.ec);
        cv.put(DatabaseInfo.SpecialisatievakColumn.SPECIALISATIE_ID, this.specialisatie_id);
        return cv;
    }
}
