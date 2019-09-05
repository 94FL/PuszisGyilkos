package com.tibor.szucs.puszisgyilkos;

import android.text.Editable;

import java.io.Serializable;

public class nev implements Serializable {
    private int puszipajtas;
    private String nev;

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev.toString();
    }

    public int getPuszipajtas() {
        return puszipajtas;
    }

    public void setPuszipajtas(int puszipajtas) {
        this.puszipajtas = puszipajtas;
    }
}