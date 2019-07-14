package com.ahmadfauzirahman.sakato.response;

import com.ahmadfauzirahman.sakato.model.PerbendaharaanModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PerbendaharaanResponse {
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Data")
    @Expose
    private List<PerbendaharaanModel> data = null;

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

    public List<PerbendaharaanModel> getDataPerbendaharaan() {
        return data;
    }

    public void setData(List<PerbendaharaanModel> data) {
        this.data = data;
    }

}
