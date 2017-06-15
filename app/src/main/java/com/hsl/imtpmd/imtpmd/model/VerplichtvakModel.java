package com.hsl.imtpmd.imtpmd.model;

/**
 * Created by jobvink on 15-06-17.
 */

public class VerplichtvakModel {
    public String id;
    public String code;
    public String naam;
    public String ec;
    public String jaar_id;
    public String periode;

    public VerplichtvakModel(String id, String code, String naam, String ec, String jaar_id, String periode) {
        this.id = id;
        this.code = code;
        this.naam = naam;
        this.ec = ec;
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
}
