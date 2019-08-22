package com.ahmadfauzirahman.sakato.response;

import com.ahmadfauzirahman.sakato.model.StakeholderModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StakeholderResponse {
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Data")
    @Expose
    private List<StakeholderModel> data = null;

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

    public List<StakeholderModel> getDataStakeHolder() {
        return data;
    }

    public void setData(List<StakeholderModel> data) {
        this.data = data;
    }

}
