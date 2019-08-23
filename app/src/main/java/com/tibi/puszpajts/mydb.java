package com.tibi.puszpajts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tibi.puszpajts.dbhelper;
import com.tibi.puszpajts.nev;

public class myDB{

    private dbhelper dbhelper;

    private SQLiteDatabase database;
    final static dbstruct dbstruct = new dbstruct();

    public myDB(Context context){
        dbhelper = new dbhelper(context);
        database = dbhelper.getWritableDatabase();
    }

    public long createRecords(nev nev){
        ContentValues values = new ContentValues();
        values.put(dbstruct.NEV, nev.getNev());
        return database.insert(dbstruct.DBNEV, null, values);
    }

    public Cursor selectRecords() {
        String[] cols = dbstruct.getFields();
        Cursor mCursor = database.query(true, dbstruct.DBNEV,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }
    public int getNumberofPlayers() {
        return selectRecords().getCount();
    }
    public long removeRecords(nev nev) {
        return database.delete(dbstruct.DBNEV, dbstruct.NEV+"=?", new String[] {nev.getNev()});
    }
}