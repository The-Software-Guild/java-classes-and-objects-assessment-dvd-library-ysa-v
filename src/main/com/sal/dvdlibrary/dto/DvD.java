/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sal.dvdlibrary.dto;

/**
 *
 * @author salajrawi
 */
import java.util.Date;

public class DvD {
    private String title;
    private String ReleaseDate;
    private String MPAA;
    private String DirectorsName;
    private String Studio;
    private String UserRating;

    public DvD(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getMPAA() {
        return MPAA;
    }

    public void setMPAA(String MPAA) {
        this.MPAA = MPAA;
    }

    public String getDirectorsName() {
        return DirectorsName;
    }

    public void setDirectorsName(String directorsName) {
        DirectorsName = directorsName;
    }

    public String getStudio() {
        return Studio;
    }

    public void setStudio(String studio) {
        Studio = studio;
    }

    public String getUserRating() {
        return UserRating;
    }

    public void setUserRating(String userRating) {
        UserRating = userRating;
    }
}
