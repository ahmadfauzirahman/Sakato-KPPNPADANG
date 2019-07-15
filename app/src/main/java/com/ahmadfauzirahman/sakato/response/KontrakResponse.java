package com.ahmadfauzirahman.sakato.response;

import com.ahmadfauzirahman.sakato.model.KontrakModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KontrakResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<KontrakModel> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatusKontrak() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<KontrakModel> getDataKontrak() {
        return data;
    }

    public void setData(List<KontrakModel> data) {
        this.data = data;
    }
}
