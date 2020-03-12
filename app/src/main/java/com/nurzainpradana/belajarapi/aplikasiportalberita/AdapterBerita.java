package com.nurzainpradana.belajarapi.aplikasiportalberita;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nurzainpradana.belajarapi.aplikasiportalberita.response.BeritaItem;
import com.squareup.picasso.Picasso;

import java.util.List;

class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.MyViewHolder> {
    //Buat Global Variabel untuk menampung context
    Context context;
    private List<BeritaItem> berita;

    public AdapterBerita(Context context, List<BeritaItem> berita) {
        //inisialisasi
        this.context = context;
        this.berita = berita;
    }

    @NonNull
    @Override
    public AdapterBerita.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Layout Inflatteer
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item, parent, false);

        //Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBerita.MyViewHolder holder, final int position) {
        //set widget

        holder.tvJudul .setText(berita.get(position).getJudulBerita());
        holder.tvTglTerbit.setText(berita.get(position).getTanggalPosting());

        //Dapatkan URL gambar
        final String urlGambarBerita = "http://10.237.22.227/portalberita/images/" + berita.get(position).getFoto();

        //set Image ke widget dengan menggunakan library picasso
        //karena imagenya dari internet

        Picasso.with(context).load(urlGambarBerita).into(holder.ivPosterBerita);

        //Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mulai activity detail
                Intent varIntent = new Intent(context, DetailActivity.class);

                //sisipkan data ke intent
                varIntent.putExtra("JDL_BERITA", berita.get(position).getJudulBerita());
                varIntent.putExtra("TGL_BERITA", berita.get(position).getTanggalPosting());
                varIntent.putExtra("PNS_BERITA", berita.get(position).getPenulis());
                varIntent.putExtra("FTO_BERITA", urlGambarBerita);
                varIntent.putExtra("ISI_BERITA", berita.get(position).getIsiBerita());

                //method startActivity cuma bisa di pake di activity/fragment
                //jad harus masuk ke context dulu
                context.startActivity(varIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return berita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //Deklarasi Widget
        ImageView ivPosterBerita;
        TextView tvJudul, tvTglTerbit, tvPenulis;
        public MyViewHolder(View itemView) {
            super(itemView);
            //inisialisasi widget
            ivPosterBerita = itemView.findViewById(R.id.ivPosterBerita);
            tvJudul = itemView.findViewById(R.id.tvJudulBerita);
            tvTglTerbit = itemView.findViewById(R.id.tvTglTerbit);
            tvPenulis = itemView.findViewById(R.id.tvPenulis);
        }

    }
}
