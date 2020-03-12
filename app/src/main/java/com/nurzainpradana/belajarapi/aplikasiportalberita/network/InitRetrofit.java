package com.nurzainpradana.belajarapi.aplikasiportalberita.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {
    //URL Server API
    public static String API_URL = "http://10.237.22.227/portalberita/";

    //method yang bertugas menginisialisasi class retrofit.
    //membertitahukan Retrofit kemana sih tujuan API kita.
    public static Retrofit setInit() {
        return new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //method yang bertugas menghubungkan class InitRetrofit dengan interface ApiServices
    //sehingga kita bisa menggunakan method request yang ada dalam interface ApiServices tersebut
    public static ApiServices getInstance() {
        return setInit().create(ApiServices.class);
    }
}
