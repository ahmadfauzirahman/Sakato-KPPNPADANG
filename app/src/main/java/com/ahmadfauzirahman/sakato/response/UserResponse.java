package com.ahmadfauzirahman.sakato.response;

import com.ahmadfauzirahman.sakato.model.UserModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private UserModel data;

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

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }
}
