package com.ahmadfauzirahman.sakato.response;

import com.ahmadfauzirahman.sakato.model.PengumumanModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PengumumanResponse {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<PengumumanModel> data = null;

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

    public List<PengumumanModel> getDataPengumuman() {
        return data;
    }

    public void setData(List<PengumumanModel> data) {
        this.data = data;
    }

}
