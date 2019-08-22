package com.ahmadfauzirahman.sakato.response;

import com.ahmadfauzirahman.sakato.model.InitModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InitResponse {
    @SerializedName("jumlah_loket")
    @Expose
    private String jumlahLoket;
    @SerializedName("init")
    @Expose
    private List<InitModel> init = null;

    public String getJumlahLoket() {
        return jumlahLoket;
    }

    public void setJumlahLoket(String jumlahLoket) {
        this.jumlahLoket = jumlahLoket;
    }

    public List<InitModel> getInit() {
        return init;
    }

    public void setInit(List<InitModel> init) {
        this.init = init;
    }
}
