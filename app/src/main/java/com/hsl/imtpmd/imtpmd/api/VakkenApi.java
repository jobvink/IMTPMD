package com.hsl.imtpmd.imtpmd.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.hsl.imtpmd.imtpmd.database.DatabaseHelper;
import com.hsl.imtpmd.imtpmd.database.DatabaseInfo;
import com.hsl.imtpmd.imtpmd.model.KeuzevakModel;
import com.hsl.imtpmd.imtpmd.model.SpecialisatievakModel;
import com.hsl.imtpmd.imtpmd.model.UserKeuzevakModel;
import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.UserSpecialisatievakModel;
import com.hsl.imtpmd.imtpmd.model.UserVerplichtvakModel;
import com.hsl.imtpmd.imtpmd.model.Vak;
import com.hsl.imtpmd.imtpmd.model.VerplichtvakModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jobvink on 16-06-17.
 *
 */

public class VakkenApi {
    private String baseUrl = "http://jmcvink.nl/school/imtpmd/";
    private static VakkenApi api;

    private VakkenApi() {}

    public static synchronized VakkenApi getApi(Context ctx) {
        if (api == null) {
            api = new VakkenApi();
        }
        return api;
    }

    public void requestVerplichtenVakken(final Context context){
        Type type = new TypeToken<List<VerplichtvakModel>>(){}.getType();
        GsonRequest<List<VerplichtvakModel>> request = new GsonRequest<List<VerplichtvakModel>>(baseUrl + "verplichtevakken.json",
                type, null, new Response.Listener<List<VerplichtvakModel>>() {
            @Override
            public void onResponse(List<VerplichtvakModel> response) {
                verplichtevakkenRequestSucces(context, response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                processRequestError(error);
            }
        });
        VolleyHelper.getInstance(context).addToRequestQueue(request);
    }


    private void verplichtevakkenRequestSucces(Context context, List<VerplichtvakModel> subjects ){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
        // putting all received classes in my database.
        for (VerplichtvakModel cm : subjects) {
            ContentValues cv = cm.createContentValues();
            dbHelper.insert(DatabaseInfo.VerplichtvakTables.VERPLICHTVAK, null, cv);
        }
    }

    public void requestKeuzevakken(final Context context){
        Type type = new TypeToken<List<KeuzevakModel>>(){}.getType();
        GsonRequest<List<KeuzevakModel>> request = new GsonRequest<List<KeuzevakModel>>(baseUrl + "keuzevakken.json",
                type, null, new Response.Listener<List<KeuzevakModel>>() {
            @Override
            public void onResponse(List<KeuzevakModel> response) {
                keuzevakkenRequestSucces(context, response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                processRequestError(error);
            }
        });
        VolleyHelper.getInstance(context).addToRequestQueue(request);
    }


    private void keuzevakkenRequestSucces(Context context, List<KeuzevakModel> subjects ){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
        // putting all received classes in my database.
        for (KeuzevakModel cm : subjects) {
            ContentValues cv = cm.createContentValues();
            dbHelper.insert(DatabaseInfo.KeuzevakTables.KEUZEVAK, null, cv);
        }
    }

    public void requestSpecialisatievakken(final Context context){
        Type type = new TypeToken<List<SpecialisatievakModel>>(){}.getType();
        GsonRequest<List<SpecialisatievakModel>> request = new GsonRequest<List<SpecialisatievakModel>>(baseUrl + "specialisatievakken.json",
                type, null, new Response.Listener<List<SpecialisatievakModel>>() {
            @Override
            public void onResponse(List<SpecialisatievakModel> response) {
                specialisatievakkenRequestSucces(context, response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                processRequestError(error);
            }
        });
        VolleyHelper.getInstance(context).addToRequestQueue(request);
    }


    private void specialisatievakkenRequestSucces(Context context, List<SpecialisatievakModel> subjects ){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
        // putting all received classes in my database.
        for (SpecialisatievakModel cm : subjects) {
            ContentValues cv = cm.createContentValues();
            dbHelper.insert(DatabaseInfo.SpecialisatievakTables.SPECIALISATIEVAK, null, cv);
        }
    }

    private void processRequestError(VolleyError error){
        Log.d("Error: " , error.toString());
    }

    public void seedUser (Context context, String gebruikersnaam) {
        UserModel user = UserModel.getUser(context, gebruikersnaam);

        ArrayList<VerplichtvakModel> verplichtvakModels = VerplichtvakModel.all(context);

        for (VerplichtvakModel v : verplichtvakModels) {
            new UserVerplichtvakModel(user, v, 0, 0).store(context);
        }

        ArrayList<SpecialisatievakModel> specialisatievakModels = SpecialisatievakModel.all(context);

        for (SpecialisatievakModel v : specialisatievakModels) {
            new UserSpecialisatievakModel(user, v, 0, 0).store(context);
        }

        ArrayList<KeuzevakModel> keuzevakModels = KeuzevakModel.all(context);

        for (KeuzevakModel v : keuzevakModels) {
            new UserKeuzevakModel(user, v, 0, 0).store(context);
        }

    }
}
