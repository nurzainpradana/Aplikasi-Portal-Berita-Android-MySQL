package com.nurzainpradana.belajarapi.aplikasiportalberita.network;

import com.nurzainpradana.belajarapi.aplikasiportalberita.response.ResponseBerita;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    //@TIPEMETHOD ("API_END_POINT")
    @GET("tampil_berita.php")
    //Call mengimport class milik retrofit2
    Call<ResponseBerita> request_show_all_berita();
    //<ModelData> nama_method()

}
