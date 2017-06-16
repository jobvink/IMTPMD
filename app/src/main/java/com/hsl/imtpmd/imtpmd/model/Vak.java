package com.hsl.imtpmd.imtpmd.model;

import android.content.ContentValues;

/**
 * Created by jobvink on 15-06-17.
 */

public abstract class Vak {
    public String id;
    public String code;
    public String naam;
    public String ec;

    public Vak(String id, String code, String naam, String ec) {
        this.id = id;
        this.code = code;
        this.naam = naam;
        this.ec = ec;
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
}