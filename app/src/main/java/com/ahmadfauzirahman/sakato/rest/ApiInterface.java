package com.ahmadfauzirahman.sakato.rest;

/**
 * Created by ahmadfauzirahman on 01/05/18.
 */


import com.ahmadfauzirahman.sakato.response.PerbendaharaanResponse;
import com.ahmadfauzirahman.sakato.response.UserResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ahmafauziraman on 18/04/18.
 */

public interface ApiInterface {

    // di sini adalah penghubung antara android dan server
    // untuk login
    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );

    // get all perbendaharan berdasarkan stakeholder yg login

    @GET("apigetbendahara/{id}")
    Call<PerbendaharaanResponse> bendaharaall(@Path("id") String id);

    @GET("apigetbendaharabyid/{id}")
    Call<PerbendaharaanResponse> bendaharabyid(@Path("id") String id);

    // form bendhara edit
    @FormUrlEncoded
    @POST("editperbendaharaan")
    Call<PerbendaharaanResponse> edit(
            @Field("nama") String nama,
            @Field("jabatan") String jabatan,
            @Field("email") String email,
            @Field("nohp") String nohp,
            @Field("id") String id
    );

    // form bendhara add
    @FormUrlEncoded
    @POST("apiaddperbendaharaan")
    Call<PerbendaharaanResponse> add(
            @Field("nama") String nama,
            @Field("jabatan") String jabatan,
            @Field("email") String email,
            @Field("nohp") String nohp,
            @Field("kode") String kode
    );

}
