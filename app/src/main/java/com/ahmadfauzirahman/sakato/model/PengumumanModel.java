package com.ahmadfauzirahman.sakato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PengumumanModel {
    @SerializedName("pengId")
    @Expose
    private String pengId;
    @SerializedName("pengJudul")
    @Expose
    private String pengJudul;
    @SerializedName("pengIsi")
    @Expose
    private String pengIsi;
    @SerializedName("pengAuthor")
    @Expose
    private String pengAuthor;
    @SerializedName("pengTanggalPengumuman")
    @Expose
    private String pengTanggalPengumuman;
    @SerializedName("pengStatus")
    @Expose
    private String pengStatus;
    @SerializedName("pengFile")
    @Expose
    private String pengFile;

    public String getPengId() {
        return pengId;
    }

    public void setPengId(String pengId) {
        this.pengId = pengId;
    }

    public String getPengJudul() {
        return pengJudul;
    }

    public void setPengJudul(String pengJudul) {
        this.pengJudul = pengJudul;
    }

    public String getPengIsi() {
        return pengIsi;
    }

    public void setPengIsi(String pengIsi) {
        this.pengIsi = pengIsi;
    }

    public String getPengAuthor() {
        return pengAuthor;
    }

    public void setPengAuthor(String pengAuthor) {
        this.pengAuthor = pengAuthor;
    }

    public String getPengTanggalPengumuman() {
        return pengTanggalPengumuman;
    }

    public void setPengTanggalPengumuman(String pengTanggalPengumuman) {
        this.pengTanggalPengumuman = pengTanggalPengumuman;
    }

    public String getPengStatus() {
        return pengStatus;
    }

    public void setPengStatus(String pengStatus) {
        this.pengStatus = pengStatus;
    }

    public String getPengFile() {
        return pengFile;
    }

    public void setPengFile(String pengFile) {
        this.pengFile = pengFile;
    }

}
