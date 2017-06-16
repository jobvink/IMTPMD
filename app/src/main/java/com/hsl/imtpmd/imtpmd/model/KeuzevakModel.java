package com.hsl.imtpmd.imtpmd.model;

import android.content.ContentValues;

import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;

/**
 * Created by jobvink on 15-06-17.
 */

public class KeuzevakModel extends Vak implements Model{
    public KeuzevakModel(String id, String code, String naam, String ec) {
        super(id, code, naam, ec);
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
}
