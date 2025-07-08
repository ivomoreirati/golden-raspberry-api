package com.outsera.movie.dto;

public class Movie {
    private int year;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;

    public Movie(String title, int year, String studios, String producers, boolean winner) {
        this.title = title;
        this.year = year;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getStudios() { return studios; }
    public void setStudios(String studios) { this.studios = studios; }

    public String getProducers() { return producers; }
    public void setProducers(String producers) { this.producers = producers; }

    public boolean isWinner() { return winner; }
    public void setWinner(boolean winner) { this.winner = winner; }
}