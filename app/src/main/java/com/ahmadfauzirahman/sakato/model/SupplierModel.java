package com.ahmadfauzirahman.sakato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupplierModel {
    @SerializedName("supplierID")
    @Expose
    private String supplierID;
    @SerializedName("supplierNama")
    @Expose
    private String supplierNama;
    @SerializedName("supplierKodeStaker")
    @Expose
    private String supplierKodeStaker;
    @SerializedName("supplierAlasan")
    @Expose
    private String supplierAlasan;
    @SerializedName("supplierTanggalPenolakan")
    @Expose
    private String supplierTanggalPenolakan;
    @SerializedName("supplierOperator")
    @Expose
    private String supplierOperator;
    @SerializedName("supplierStatus")
    @Expose
    private String supplierStatus;
    @SerializedName("supplierPengirimin")
    @Expose
    private String supplierPengirimin;
    @SerializedName("jenis")
    @Expose
    private String jenis;

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierNama() {
        return supplierNama;
    }

    public void setSupplierNama(String supplierNama) {
        this.supplierNama = supplierNama;
    }

    public String getSupplierKodeStaker() {
        return supplierKodeStaker;
    }

    public void setSupplierKodeStaker(String supplierKodeStaker) {
        this.supplierKodeStaker = supplierKodeStaker;
    }

    public String getSupplierAlasan() {
        return supplierAlasan;
    }

    public void setSupplierAlasan(String supplierAlasan) {
        this.supplierAlasan = supplierAlasan;
    }

    public String getSupplierTanggalPenolakan() {
        return supplierTanggalPenolakan;
    }

    public void setSupplierTanggalPenolakan(String supplierTanggalPenolakan) {
        this.supplierTanggalPenolakan = supplierTanggalPenolakan;
    }

    public String getSupplierOperator() {
        return supplierOperator;
    }

    public void setSupplierOperator(String supplierOperator) {
        this.supplierOperator = supplierOperator;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierPengirimin() {
        return supplierPengirimin;
    }

    public void setSupplierPengirimin(String supplierPengirimin) {
        this.supplierPengirimin = supplierPengirimin;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
