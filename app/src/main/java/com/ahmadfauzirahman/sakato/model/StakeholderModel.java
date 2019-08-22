package com.ahmadfauzirahman.sakato.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StakeholderModel {

    @SerializedName("stakeID")
    @Expose
    private String stakeID;
    @SerializedName("stakeNama")
    @Expose
    private String stakeNama;
    @SerializedName("stakeKode")
    @Expose
    private String stakeKode;
    @SerializedName("stakePassword")
    @Expose
    private String stakePassword;
    @SerializedName("stakeEmail")
    @Expose
    private String stakeEmail;
    @SerializedName("stakeStatus")
    @Expose
    private String stakeStatus;
    @SerializedName("stake")
    @Expose
    private String stake;

    public String getStakeID() {
        return stakeID;
    }

    public void setStakeID(String stakeID) {
        this.stakeID = stakeID;
    }

    public String getStakeNama() {
        return stakeNama;
    }

    public void setStakeNama(String stakeNama) {
        this.stakeNama = stakeNama;
    }

    public String getStakeKode() {
        return stakeKode;
    }

    public void setStakeKode(String stakeKode) {
        this.stakeKode = stakeKode;
    }

    public String getStakePassword() {
        return stakePassword;
    }

    public void setStakePassword(String stakePassword) {
        this.stakePassword = stakePassword;
    }

    public String getStakeEmail() {
        return stakeEmail;
    }

    public void setStakeEmail(String stakeEmail) {
        this.stakeEmail = stakeEmail;
    }

    public String getStakeStatus() {
        return stakeStatus;
    }

    public void setStakeStatus(String stakeStatus) {
        this.stakeStatus = stakeStatus;
    }

    public String getStake() {
        return stake;
    }

    public void setStake(String stake) {
        this.stake = stake;
    }
}
