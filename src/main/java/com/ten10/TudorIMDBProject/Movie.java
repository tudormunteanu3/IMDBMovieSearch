package com.ten10.TudorIMDBProject;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(indexes = {@Index(name = "idx_primaryTitle", columnList = "primaryTitle ASC")})
public class Movie implements Serializable {

    @Id
    private String tconst;
    private String titleType;
    @Column(nullable = false, length = 500)
    private String primaryTitle;
    private double averageRating;
    private int numVotes;
    private String posterUrl;

    protected Movie() {
    }

    public Movie(String tconst, String titleType, String primaryTitle, double averageRating, int numVotes) {
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    @Override
    public String toString() {
        return String.format(
                "Show:[tconst=%d, type=%s, name='%s']",
                tconst, titleType, primaryTitle);
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    @Lob
    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}