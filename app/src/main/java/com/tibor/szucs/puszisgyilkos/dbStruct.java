package com.tibor.szucs.puszisgyilkos;

public class dbStruct {
    public String DBNEV = "nevek";
    public String ID = "id";
    public String ID_TYPE = "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL";
    public String NEV = "nev";
    public String NEV_TYPE = "VARCHAR(32) UNIQUE NOT NULL";
    public String[] getFields() {
        return new String[]{ID, NEV};
    }
}