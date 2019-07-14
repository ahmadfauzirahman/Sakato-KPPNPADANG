package com.ahmadfauzirahman.sakato.response;

import com.ahmadfauzirahman.sakato.model.SpmModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpmResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<SpmModel> data;

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

    public List<SpmModel> getData() {
        return data;
    }

    public void setData(List<SpmModel> data) {
        this.data = data;
    }

}
