package com.tibi.puszpajts;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper{
    final static dbstruct dbstruct = new dbstruct();

    public dbhelper(Context context) {
        super(context, dbstruct.DBNEV, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+dbstruct.DBNEV+" ("+dbstruct.ID+" "+dbstruct.ID_TYPE+", "+dbstruct.NEV+" "+dbstruct.NEV_TYPE+");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+dbstruct.DBNEV+";");
        onCreate(db);
    }
}