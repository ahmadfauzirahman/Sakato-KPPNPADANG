package com.ahmadfauzirahman.sakato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("penID")
    @Expose
    private String penID;
    @SerializedName("penNama")
    @Expose
    private String penNama;
    @SerializedName("penUsername")
    @Expose
    private String penUsername;
    @SerializedName("penPassword")
    @Expose
    private String penPassword;
    @SerializedName("penLvlAkses")
    @Expose
    private String penLvlAkses;

    public String getPenID() {
        return penID;
    }

    public void setPenID(String penID) {
        this.penID = penID;
    }

    public String getPenNama() {
        return penNama;
    }

    public void setPenNama(String penNama) {
        this.penNama = penNama;
    }

    public String getPenUsername() {
        return penUsername;
    }

    public void setPenUsername(String penUsername) {
        this.penUsername = penUsername;
    }

    public String getPenPassword() {
        return penPassword;
    }

    public void setPenPassword(String penPassword) {
        this.penPassword = penPassword;
    }

    public String getPenLvlAkses() {
        return penLvlAkses;
    }

    public void setPenLvlAkses(String penLvlAkses) {
        this.penLvlAkses = penLvlAkses;
    }
}
