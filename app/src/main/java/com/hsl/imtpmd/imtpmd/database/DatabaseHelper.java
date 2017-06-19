package com.hsl.imtpmd.imtpmd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.hsl.imtpmd.imtpmd.model.VerplichtvakModel;
import java.util.ArrayList;

/**
 * Created by Job Vink on 15-6-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    public static final String dbName = "imtpmd.db";
    public static final int dbVersion = 22;        // Versie nr van je db.

    public DatabaseHelper(Context ctx) {
        super(ctx, dbName, null, dbVersion);    // gebruik de super constructor.
    }

    // synchronized … dit zorgt voor . . . . (?)
    // welk design pattern is dit ??
    public static synchronized DatabaseHelper getHelper(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx);
            mSQLDB = mInstance.getWritableDatabase();
        }
        return mInstance;
    }

    @Override										// Maak je tabel met deze kolommen
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DatabaseInfo.VerplichtvakTables.VERPLICHTVAK + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DatabaseInfo.VerplichtvakColumn.ID + " TEXT, " +
            DatabaseInfo.VerplichtvakColumn.CODE + " TEXT, " +
            DatabaseInfo.VerplichtvakColumn.NAAM + " TEXT, " +
            DatabaseInfo.VerplichtvakColumn.EC + " TEXT, " +
            DatabaseInfo.VerplichtvakColumn.JAAR_ID + " TEXT, " +
            DatabaseInfo.VerplichtvakColumn.PERIODE + " TEXT);"
        );
        db.execSQL("CREATE TABLE " + DatabaseInfo.KeuzevakTables.KEUZEVAK + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.KeuzevakColumn.ID + " TEXT, " +
                DatabaseInfo.KeuzevakColumn.CODE + " TEXT, " +
                DatabaseInfo.KeuzevakColumn.NAAM + " TEXT, " +
                DatabaseInfo.KeuzevakColumn.EC + " TEXT);"
        );
        db.execSQL("CREATE TABLE " + DatabaseInfo.SpecialisatievakTables.SPECIALISATIEVAK + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.SpecialisatievakColumn.ID + " TEXT, " +
                DatabaseInfo.SpecialisatievakColumn.CODE + " TEXT, " +
                DatabaseInfo.SpecialisatievakColumn.NAAM + " TEXT, " +
                DatabaseInfo.SpecialisatievakColumn.EC + " TEXT, " +
                DatabaseInfo.SpecialisatievakColumn.SPECIALISATIE_ID + " TEXT);"
        );
        db.execSQL("CREATE TABLE " + DatabaseInfo.UserTables.USER + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.UserColumn.ID + " TEXT, " +
                DatabaseInfo.UserColumn.GEBRUIKERSNAAM + " TEXT, " +
                DatabaseInfo.UserColumn.WACHTWOORD + " TEXT);"
        );
        db.execSQL("CREATE TABLE " + DatabaseInfo.User_VerplichtvakTables.User_Verplichtvak + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.User_verplichtvakColumn.USER_ID + " TEXT, " +
                DatabaseInfo.User_verplichtvakColumn.VERPLICHTVAK_ID + " TEXT, " +
                DatabaseInfo.User_verplichtvakColumn.BEHAALD + " INTEGER);"
        );
        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s) VALUES ('%s', '%s', '%s');", DatabaseInfo.UserTables.USER, DatabaseInfo.UserColumn.ID, DatabaseInfo.UserColumn.GEBRUIKERSNAAM, DatabaseInfo.UserColumn.WACHTWOORD, "1", "test" , "test"));
    }
    // CREATE TABLE course (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, ects TEXT, grade TEXT);

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.VerplichtvakTables.VERPLICHTVAK);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.KeuzevakTables.KEUZEVAK);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.SpecialisatievakTables.SPECIALISATIEVAK);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.UserTables.USER);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.User_VerplichtvakTables.User_Verplichtvak);
        onCreate(db);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }

    public void update(String table, ContentValues cv, String id){
        mSQLDB.update(table, cv, "id=?",new String[]{id});
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }

    public ArrayList<VerplichtvakModel> verplichtevakkenVanJaar(int jaar){

        Cursor rs = this.query(DatabaseInfo.VerplichtvakTables.VERPLICHTVAK, new String[]{"*"}, DatabaseInfo.VerplichtvakColumn.JAAR_ID + " = " + jaar, null, null, null, null);

        rs.moveToFirst();

        Log.d("DatabaseHelper: " , "Beginnen met ophalen uit de database" );

        ArrayList<VerplichtvakModel> verplichtvakModelArrayList = new ArrayList<VerplichtvakModel>();

        do {
            try {
                verplichtvakModelArrayList.add(new VerplichtvakModel(
                        rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.ID)),
                        rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.CODE)),
                        rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.NAAM)),
                        rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.EC)),
                        rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.JAAR_ID)),
                        rs.getString(rs.getColumnIndex(DatabaseInfo.VerplichtvakColumn.PERIODE))
                ));
            } catch (Exception e) {
                Log.e("error: " , e.toString());
            }
        } while (rs.moveToNext());

        return verplichtvakModelArrayList;
    }

}
