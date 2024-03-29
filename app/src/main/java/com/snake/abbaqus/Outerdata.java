package com.snake.abbaqus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Outerdata {
    @SerializedName("modhash")
    @Expose
    private String modhash;
    @SerializedName("dist")
    @Expose
    private int dist;
    @SerializedName("children")
    @Expose
    private List<Children> children;
    @SerializedName("after")
    @Expose
    private String after;
    @SerializedName("before")
    @Expose
    private String before;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }
}
