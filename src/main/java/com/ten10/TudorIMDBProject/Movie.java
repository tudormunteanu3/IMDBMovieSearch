package com.ten10.TudorIMDBProject;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(indexes = @Index(name = "index_primaryTitle", columnList = "primaryTitle ASC"))
public class Movie implements Serializable {

    @Id
    private int tconst;
    private String titleType;
    @Lob
    private String primaryTitle;

    protected Movie() {}

    public Movie(int tconst, String titleType, String primaryTitle){
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
    }

    @Override
    public String toString() {
        return String.format(
                "Show:[tconst=%d, type=%s, name='%s']",
                tconst, titleType,primaryTitle);
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

    public int getTconst() { return tconst; }

    public void setTconst(int tconst) { this.tconst = tconst;}
}