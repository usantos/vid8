package br.com.cinesky.model;


import java.util.List;

public class Filme {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getBackdrops_url() {
        return backdrops_url;
    }

    public void setBackdrops_url(String backdrops_url) {
        this.backdrops_url = backdrops_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String title;
    private String overview;
    private String duration;
    private String release_year;
    private String cover_url;
    private String backdrops_url;
    private String id;

    private List<Filme> itens;

    public List<Filme> getItens() {
        return itens;
    }

    public void setItens(List<Filme> itens) {
        this.itens = itens;
    }


}
