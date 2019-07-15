package com.ahmadfauzirahman.sakato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KontrakModel {
    @SerializedName("kontrakId")
    @Expose
    private String kontrakId;
    @SerializedName("kontrakKodeStaker")
    @Expose
    private String kontrakKodeStaker;
    @SerializedName("kontrakNomor")
    @Expose
    private String kontrakNomor;
    @SerializedName("kontrakNamaRekanan")
    @Expose
    private String kontrakNamaRekanan;
    @SerializedName("kontrakAlasanPenolakan")
    @Expose
    private String kontrakAlasanPenolakan;
    @SerializedName("kontrakTanggalPenolakan")
    @Expose
    private String kontrakTanggalPenolakan;
    @SerializedName("kontrakOperator")
    @Expose
    private String kontrakOperator;
    @SerializedName("kontrakStatus")
    @Expose
    private String kontrakStatus;
    @SerializedName("kontrakPengiriman")
    @Expose
    private String kontrakPengiriman;
    @SerializedName("jenis")
    @Expose
    private String jenis;

    public String getKontrakId() {
        return kontrakId;
    }

    public void setKontrakId(String kontrakId) {
        this.kontrakId = kontrakId;
    }

    public String getKontrakKodeStaker() {
        return kontrakKodeStaker;
    }

    public void setKontrakKodeStaker(String kontrakKodeStaker) {
        this.kontrakKodeStaker = kontrakKodeStaker;
    }

    public String getKontrakNomor() {
        return kontrakNomor;
    }

    public void setKontrakNomor(String kontrakNomor) {
        this.kontrakNomor = kontrakNomor;
    }

    public String getKontrakNamaRekanan() {
        return kontrakNamaRekanan;
    }

    public void setKontrakNamaRekanan(String kontrakNamaRekanan) {
        this.kontrakNamaRekanan = kontrakNamaRekanan;
    }

    public String getKontrakAlasanPenolakan() {
        return kontrakAlasanPenolakan;
    }

    public void setKontrakAlasanPenolakan(String kontrakAlasanPenolakan) {
        this.kontrakAlasanPenolakan = kontrakAlasanPenolakan;
    }

    public String getKontrakTanggalPenolakan() {
        return kontrakTanggalPenolakan;
    }

    public void setKontrakTanggalPenolakan(String kontrakTanggalPenolakan) {
        this.kontrakTanggalPenolakan = kontrakTanggalPenolakan;
    }

    public String getKontrakOperator() {
        return kontrakOperator;
    }

    public void setKontrakOperator(String kontrakOperator) {
        this.kontrakOperator = kontrakOperator;
    }

    public String getKontrakStatus() {
        return kontrakStatus;
    }

    public void setKontrakStatus(String kontrakStatus) {
        this.kontrakStatus = kontrakStatus;
    }

    public String getKontrakPengiriman() {
        return kontrakPengiriman;
    }

    public void setKontrakPengiriman(String kontrakPengiriman) {
        this.kontrakPengiriman = kontrakPengiriman;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

}
