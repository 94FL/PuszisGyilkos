package com.tibor.szucs.puszisgyilkos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tibor.szucs.puszisgyilkos.*;

public class myDB{

    private dbHelper dbhelper;

    private static SQLiteDatabase database;
    final static dbStruct dbstruct = new dbStruct();

    public myDB(Context context){
        dbhelper = new dbHelper(context);
        database = dbhelper.getWritableDatabase();
    }

    public static long createRecords(nev nev){
        ContentValues values = new ContentValues();
        values.put(dbstruct.NEV, nev.getNev());
        return database.insertWithOnConflict(dbstruct.DBNEV, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public Cursor selectRecords() {
        String[] cols = dbstruct.getFields();
        Cursor mCursor = database.query(true, dbstruct.DBNEV,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public int getNumberofPlayers() {
        return selectRecords().getCount();
    }
    public long removeAllRecords() {
        return database.delete(dbstruct.DBNEV, "1=1", new String[] {});
    }
    public long removeRecordsByName(nev nev) {
        return database.delete(dbstruct.DBNEV, dbstruct.NEV+"=?", new String[] {nev.getNev()});
    }
}