package com.aa183.rizkianto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper{
    private final static  int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_film";
    private final static String TABLE_FILM = "t_film";
    private final static String KEY_ID_FILM = "ID_Film";
    private final static String KEY_JUDUL = "Judul_Film";
    private final static String KEY_TGL = "Tanggal_Rilis";
    private final static String KEY_POSTER = "Poster";
    private final static String KEY_SUTRADARA = "Sutradara";
    private final static String KEY_TAGLINE = "Tagline";
    private final static String KEY_SINOPSIS = "Sinopsis";
    private final static String KEY_LINK = "Link_Film";
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());
    private Context context;

    public DatabaseHandler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FILM = "CREATE TABLE " + TABLE_FILM
                + "(" + KEY_ID_FILM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_TGL + " DATE, "
                + KEY_POSTER + " TEXT, " + KEY_SUTRADARA + " TEXT, "
                + KEY_TAGLINE + " TEXT, " + KEY_SINOPSIS + " TEXT, "
                + KEY_LINK + " TEXT);";
        db.execSQL(CREATE_TABLE_FILM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_FILM;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahFilm(Film dataFilm){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudulFilm());
        cv.put(KEY_TGL, sdFormat.format(dataFilm.getTanggalRilis()));
        cv.put(KEY_POSTER, dataFilm.getPoster());
        cv.put(KEY_SUTRADARA, dataFilm.getSutradara());
        cv.put(KEY_TAGLINE, dataFilm.getTagline());
        cv.put(KEY_SINOPSIS, dataFilm.getSinopsis());
        cv.put(KEY_LINK, dataFilm.getLinkFilm());

        db.insert(TABLE_FILM, null, cv);
        db.close();
    }

    public void editFilm(Film dataFilm){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudulFilm());
        cv.put(KEY_TGL, sdFormat.format(dataFilm.getTanggalRilis()));
        cv.put(KEY_POSTER, dataFilm.getPoster());
        cv.put(KEY_SUTRADARA, dataFilm.getSutradara());
        cv.put(KEY_TAGLINE, dataFilm.getTagline());
        cv.put(KEY_SINOPSIS, dataFilm.getSinopsis());
        cv.put(KEY_LINK, dataFilm.getLinkFilm());

        db.update(TABLE_FILM, cv, KEY_ID_FILM + "=?", new String[]{String.valueOf(dataFilm.getIdFilm())});
        db.close();
    }

    public void hapusFilm(int idFilm){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_FILM, KEY_ID_FILM + "=?", new String[]{String.valueOf(idFilm)});
        db.close();
    }

    public ArrayList<Film> getAllFilm(){
        ArrayList<Film> dataFilm = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_FILM;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);

        if(csr.moveToFirst()){
            do{
                Date tempDate = new Date();
                try{
                    tempDate = sdFormat.parse(csr.getString(2));
                }catch (ParseException er){
                    er.printStackTrace();
                }

                Film tempFilm = new Film(
                        csr.getInt(0),
                        csr.getString(1),
                        tempDate,
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6),
                        csr.getString(7)
                );
                dataFilm.add(tempFilm);
            }while(csr.moveToNext());
        }
        return dataFilm;
    }

    private String storeImageFile(int id){
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }
}
