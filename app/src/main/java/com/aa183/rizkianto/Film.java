package com.aa183.rizkianto;

import java.util.Date;

public class Film {
    private int idFilm;
    private String judulFilm;
    private Date tanggalRilis;
    private String poster;
    private String sutradara;
    private String tagline;
    private String sinopsis;
    private String linkFilm;

    public Film(int idFilm, String judulFilm, Date tanggalRilis, String poster, String sutradara, String tagline, String sinopsis, String linkFilm){
        this.idFilm = idFilm;
        this.judulFilm = judulFilm;
        this.tanggalRilis = tanggalRilis;
        this.poster = poster;
        this.sutradara = sutradara;
        this.tagline = tagline;
        this.sinopsis = sinopsis;
        this.linkFilm = linkFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setJudulFilm(String judulFilm) {
        this.judulFilm = judulFilm;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public void setTanggalRilis(Date tanggalRilis) {
        this.tanggalRilis = tanggalRilis;
    }

    public Date getTanggalRilis() {
        return tanggalRilis;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPoster() {
        return poster;
    }

    public void setSutradara(String sutradara) {
        this.sutradara = sutradara;
    }

    public String getSutradara() {
        return sutradara;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTagline() {
        return tagline;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setLinkFilm(String linkFilm) {
        this.linkFilm = linkFilm;
    }

    public String getLinkFilm() {
        return linkFilm;
    }
}
