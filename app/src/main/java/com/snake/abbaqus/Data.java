package com.snake.abbaqus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private Outerdata outerdata;


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Outerdata getOuterdata() {
        return outerdata;
    }

    public void setOuterdata(Outerdata outerdata) {
        this.outerdata = outerdata;
    }
}
