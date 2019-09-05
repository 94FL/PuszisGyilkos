package com.tibor.szucs.puszisgyilkos;

import android.text.Editable;

public class nev {
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