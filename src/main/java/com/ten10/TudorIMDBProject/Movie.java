package com.ten10.TudorIMDBProject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    private int tconst;
    private String titleType;
    private String primaryTitle;

    protected Movie(int tconst) {
        this.tconst = tconst;
    }

    public Movie(int tconst, String titleType, String primaryTitle){
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
    }

    @Override
    public String toString() {
        return String.format(
                "Show:[type=%d, name='%s']",
                titleType,primaryTitle);
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }
}