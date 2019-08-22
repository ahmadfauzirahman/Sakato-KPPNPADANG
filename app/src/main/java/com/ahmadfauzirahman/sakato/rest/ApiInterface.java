package com.ahmadfauzirahman.sakato.rest;

/**
 * Created by ahmadfauzirahman on 01/05/18.
 */


import com.ahmadfauzirahman.sakato.response.InitResponse;
import com.ahmadfauzirahman.sakato.response.KontrakResponse;
import com.ahmadfauzirahman.sakato.response.PengumumanResponse;
import com.ahmadfauzirahman.sakato.response.PerbendaharaanResponse;
import com.ahmadfauzirahman.sakato.response.SpmResponse;
import com.ahmadfauzirahman.sakato.response.StakeholderResponse;
import com.ahmadfauzirahman.sakato.response.SupplierResponse;
import com.ahmadfauzirahman.sakato.response.TokenResponse;
import com.ahmadfauzirahman.sakato.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    //spm

    @GET("spmbykd/{id}")
    Call<SpmResponse> spmbykd(@Path("id") String id);


    @GET("spmbyid/{id}")
    Call<SpmResponse> spmbyid(@Path("id") String id);

    //kontrak
    @GET("spmupdate/{id}/{jenis}")
    Call<SpmResponse> updatePending(@Path("id") String id, @Path("jenis") String jenis);

    //kontrak
    @GET("kontrakbykd/{id}")
    Call<KontrakResponse> kontrakbykd(@Path("id") String id);

    //supplier
    @GET("supbykd/{id}")
    Call<SupplierResponse> supbykd(@Path("id") String id);

    //profile
    @GET("profile/{id}")
    Call<StakeholderResponse> profile(@Path("id") String id);

    // form bendhara add
    @FormUrlEncoded
    @POST("editprofile")
    Call<StakeholderResponse> edit(
            @Field("stakeNama") String stakeNama,
            @Field("stakeKode") String stakeKode,
            @Field("stakeEmail") String stakeEmail,
            @Field("stake") String stake
    );


    @FormUrlEncoded
    @POST("inserttoken")
    Call<TokenResponse> inserttoken(
            @Field("stakeholder") String stakeholder,
            @Field("token") String token
    );


    // form bendhara add
    @FormUrlEncoded
    @POST("deletetoken")
    Call<TokenResponse> deletetoken(
            @Field("stakeKode") String stakeKode
    );

    // pengumuman
    @GET("pengumuman")
    Call<PengumumanResponse> pengumuman();

    // pengumuman
    @GET("monitoringdaemon")
    Call<InitResponse> jumlahloket();

    // pengumumanby id
    @GET("pengumumanbyid/{id}")
    Call<PengumumanResponse> pengumumanbyid(@Path("id") String id);
}
