package com.ahmadfauzirahman.sakato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PerbendaharaanModel {
    @SerializedName("penId")
    @Expose
    private String penId;
    @SerializedName("penNamaLengkap")
    @Expose
    private String penNamaLengkap;
    @SerializedName("penJabatan")
    @Expose
    private String penJabatan;
    @SerializedName("penEmail")
    @Expose
    private String penEmail;
    @SerializedName("penNoHp")
    @Expose
    private String penNoHp;
    @SerializedName("penStakeholder")
    @Expose
    private String penStakeholder;

    public String getPenId() {
        return penId;
    }

    public void setPenId(String penId) {
        this.penId = penId;
    }

    public String getPenNamaLengkap() {
        return penNamaLengkap;
    }

    public void setPenNamaLengkap(String penNamaLengkap) {
        this.penNamaLengkap = penNamaLengkap;
    }

    public String getPenJabatan() {
        return penJabatan;
    }

    public void setPenJabatan(String penJabatan) {
        this.penJabatan = penJabatan;
    }

    public String getPenEmail() {
        return penEmail;
    }

    public void setPenEmail(String penEmail) {
        this.penEmail = penEmail;
    }

    public String getPenNoHp() {
        return penNoHp;
    }

    public void setPenNoHp(String penNoHp) {
        this.penNoHp = penNoHp;
    }

    public String getPenStakeholder() {
        return penStakeholder;
    }

    public void setPenStakeholder(String penStakeholder) {
        this.penStakeholder = penStakeholder;
    }

}
