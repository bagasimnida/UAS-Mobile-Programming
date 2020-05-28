package com.aa183.rizkianto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class TampilActivity extends AppCompatActivity {
    private ImageView imgFilm;
    private TextView tvJudulFilm, tvTanggalRilis, tvSutradara, tvTagline, tvSinopsis;
    private String linkFilm;
    private Button btn_trailer;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgFilm = findViewById(R.id.iv_poster);
        tvJudulFilm = findViewById(R.id.tv_judul);
        tvTanggalRilis = findViewById(R.id.tv_tanggal);
        tvSutradara = findViewById(R.id.tv_sutradara);
        tvSinopsis = findViewById(R.id.tv_sinopsis);
        tvTagline = findViewById(R.id.tv_tagline);
        btn_trailer = findViewById(R.id.btn_trailer);

        Intent terimaData = getIntent();
        tvJudulFilm.setText(terimaData.getStringExtra("JUDUL"));
        tvTanggalRilis.setText(terimaData.getStringExtra("TANGGAL"));
        tvSutradara.setText(terimaData.getStringExtra("SUTRADARA"));
        tvTagline.setText(terimaData.getStringExtra("TAGLINE"));
        tvSinopsis.setText(terimaData.getStringExtra("SINOPSIS"));
        String imgLocation = terimaData.getStringExtra("POSTER");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgFilm.setImageBitmap(bitmap);
            imgFilm.setContentDescription(imgLocation);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "Gagal Mengambil Gambar dari Media Penyimpanan", Toast.LENGTH_SHORT).show();
        }

        linkFilm = terimaData.getStringExtra("LINK");

        btn_trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(linkFilm));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_bagikan){
            Intent bagikanBerita = new Intent(Intent.ACTION_SEND);
            bagikanBerita.putExtra(Intent.EXTRA_SUBJECT, tvJudulFilm.getText().toString());
            bagikanBerita.putExtra(Intent.EXTRA_SUBJECT, tvTagline.getText().toString());
            bagikanBerita.putExtra(Intent.EXTRA_TEXT, linkFilm);
            bagikanBerita.setType("text/plain");
            startActivity(Intent.createChooser(bagikanBerita, "Bagikan Film"));
        }
        return super.onOptionsItemSelected(item);
    }

}
