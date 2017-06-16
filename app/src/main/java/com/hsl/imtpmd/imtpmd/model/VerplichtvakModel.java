package com.hsl.imtpmd.imtpmd.model;

import android.content.ContentValues;
import android.util.Log;

import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;

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

    public String toString() {
        return "dit is een verplicht vak: " + this.naam + " uit jaar: " + this.jaar_id;
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

}
