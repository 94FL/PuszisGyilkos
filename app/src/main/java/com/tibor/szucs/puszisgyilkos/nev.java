package com.tibor.szucs.puszisgyilkos;

import android.text.Editable;

import java.io.Serializable;

public class nev implements Serializable {
    private int id;
    private String nev;

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int puszipajtas) {
        this.id = id;
    }
}