package com.ahmadfauzirahman.sakato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpmModel {

    @SerializedName("spmID")
    @Expose
    private String spmID;
    @SerializedName("spmKodeStaker")
    @Expose
    private String spmKodeStaker;
    @SerializedName("spmNomor")
    @Expose
    private String spmNomor;
    @SerializedName("spmJenis")
    @Expose
    private String spmJenis;
    @SerializedName("spamPenolakan")
    @Expose
    private String spamPenolakan;
    @SerializedName("spmTanggalPenolakan")
    @Expose
    private String spmTanggalPenolakan;
    @SerializedName("spmOperator")
    @Expose
    private String spmOperator;
    @SerializedName("spmStatus")
    @Expose
    private String spmStatus;
    @SerializedName("spmPengiriman")
    @Expose
    private String spmPengiriman;
    @SerializedName("jenis")
    @Expose
    private String jenis;

    public String getSpmID() {
        return spmID;
    }

    public void setSpmID(String spmID) {
        this.spmID = spmID;
    }

    public String getSpmKodeStaker() {
        return spmKodeStaker;
    }

    public void setSpmKodeStaker(String spmKodeStaker) {
        this.spmKodeStaker = spmKodeStaker;
    }

    public String getSpmNomor() {
        return spmNomor;
    }

    public void setSpmNomor(String spmNomor) {
        this.spmNomor = spmNomor;
    }

    public String getSpmJenis() {
        return spmJenis;
    }

    public void setSpmJenis(String spmJenis) {
        this.spmJenis = spmJenis;
    }

    public String getSpamPenolakan() {
        return spamPenolakan;
    }

    public void setSpamPenolakan(String spamPenolakan) {
        this.spamPenolakan = spamPenolakan;
    }

    public String getSpmTanggalPenolakan() {
        return spmTanggalPenolakan;
    }

    public void setSpmTanggalPenolakan(String spmTanggalPenolakan) {
        this.spmTanggalPenolakan = spmTanggalPenolakan;
    }

    public String getSpmOperator() {
        return spmOperator;
    }

    public void setSpmOperator(String spmOperator) {
        this.spmOperator = spmOperator;
    }

    public String getSpmStatus() {
        return spmStatus;
    }

    public void setSpmStatus(String spmStatus) {
        this.spmStatus = spmStatus;
    }

    public String getSpmPengiriman() {
        return spmPengiriman;
    }

    public void setSpmPengiriman(String spmPengiriman) {
        this.spmPengiriman = spmPengiriman;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
