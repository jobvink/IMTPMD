package com.hsl.imtpmd.imtpmd.model;

import android.content.ContentValues;

import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;

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
