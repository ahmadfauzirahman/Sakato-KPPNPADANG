package com.ahmadfauzirahman.sakato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InitModel {
    @SerializedName("Loket")
    @Expose
    private String  loket;
    @SerializedName("NomorAntrian")
    @Expose
    private String nomorAntrian;

    public String getLoket() {
        return loket;
    }

    public void setLoket(String loket) {
        this.loket = loket;
    }

    public String getNomorAntrian() {
        return nomorAntrian;
    }

    public void setNomorAntrian(String nomorAntrian) {
        this.nomorAntrian = nomorAntrian;
    }
}
