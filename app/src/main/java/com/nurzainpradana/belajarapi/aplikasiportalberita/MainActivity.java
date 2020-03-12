package com.nurzainpradana.belajarapi.aplikasiportalberita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.nurzainpradana.belajarapi.aplikasiportalberita.network.ApiServices;
import com.nurzainpradana.belajarapi.aplikasiportalberita.network.InitRetrofit;
import com.nurzainpradana.belajarapi.aplikasiportalberita.response.BeritaItem;
import com.nurzainpradana.belajarapi.aplikasiportalberita.response.ResponseBerita;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Deklarasi Widget
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);

        //RecyclerView harus pakai Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Eksekusi method
        tampilBerita();
    }

    private void tampilBerita() {
        ApiServices api = InitRetrofit.getInstance();

        //siapkan request
        Call<ResponseBerita> beritaCall = api.request_show_all_berita();

        //Kirim request
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                //pastikan response sukses
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    //tampung data response body ke variable
                    List<BeritaItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();

                    //kalau response status nya = true
                    if(status){
                        //buat Adapter untuk recycler view
                        AdapterBerita adapter = new AdapterBerita(MainActivity.this, data_berita);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        //kalau tidak true
                        Toast.makeText(MainActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                    //print ke log jika error
                t.printStackTrace();
            }
        });

    }
}
