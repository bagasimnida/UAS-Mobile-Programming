package com.aa183.rizkianto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder>{
    private Context context;
    private ArrayList<Film> dataFilm;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public FilmAdapter(Context context, ArrayList<Film> dataFilm) {
        this.context = context;
        this.dataFilm = dataFilm;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_film, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Film tempFilm = dataFilm.get(position);
        holder.idFilm = tempFilm.getIdFilm();
        holder.poster = tempFilm.getPoster();
        holder.tv_judul_film.setText(tempFilm.getJudulFilm());
        holder.tv_tagline.setText(tempFilm.getTagline());
        holder.sutradara.setText(tempFilm.getSutradara());
        holder.tanggalRilis.setText(sdFormat.format(tempFilm.getTanggalRilis()));
        holder.sinopsis.setText(tempFilm.getSinopsis());
        holder.linkFilm = tempFilm.getLinkFilm();
        try {
            File file = new File(holder.poster);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgFilm.setImageBitmap(bitmap);
            holder.imgFilm.setContentDescription(holder.poster);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "Gagal Mengambil Gambar dari Media Penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dataFilm.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ImageView imgFilm;
        private TextView tv_judul_film, tv_tagline, tanggalRilis, sutradara, sinopsis;
        private int idFilm;
        private String poster, linkFilm;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFilm = itemView.findViewById(R.id.iv_poster);
            tv_judul_film = itemView.findViewById(R.id.tv_judul);
            tv_tagline = itemView.findViewById(R.id.tv_tagline);
            tanggalRilis = itemView.findViewById(R.id.tv_tanggal);
            sutradara = itemView.findViewById(R.id.tv_sutradara);
            sinopsis = itemView.findViewById(R.id.tv_sinopsis);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent bukaFilm = new Intent(context, TampilActivity.class);
            bukaFilm.putExtra("ID", idFilm);
            bukaFilm.putExtra("JUDUL", tv_judul_film.getText());
            bukaFilm.putExtra("TANGGAL", tanggalRilis.getText());
            bukaFilm.putExtra("POSTER", poster);
            bukaFilm.putExtra("SUTRADARA", sutradara.getText());
            bukaFilm.putExtra("SINOPSIS", sinopsis.getText());
            bukaFilm.putExtra("TAGLINE", tv_tagline.getText());
            bukaFilm.putExtra("LINK", linkFilm);
            context.startActivity(bukaFilm);
        }

        @Override
        public boolean onLongClick(View v) {
            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI", "update");
            bukaInput.putExtra("ID", idFilm);
            bukaInput.putExtra("JUDUL", tv_judul_film.getText());
            bukaInput.putExtra("TANGGAL", tanggalRilis.getText());
            bukaInput.putExtra("POSTER", poster);
            bukaInput.putExtra("SUTRADARA", sutradara.getText());
            bukaInput.putExtra("SINOPSIS", sinopsis.getText());
            bukaInput.putExtra("TAGLINE", tv_tagline.getText());
            bukaInput.putExtra("LINK", linkFilm);
            context.startActivity(bukaInput);

            return true;
        }
    }
}
