package com.ahmadfauzirahman.sakato.response;

import com.ahmadfauzirahman.sakato.model.SupplierModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SupplierResponse {
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Data")
    @Expose
    private List<SupplierModel> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<SupplierModel> getDataSupplier() {
        return data;
    }

    public void setData(List<SupplierModel> data) {
        this.data = data;
    }

}
