package com.snake.abbaqus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InnerData {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("public_description")
    @Expose
    private String publicDescription;
    @SerializedName("banner_img")
    @Expose
    private String bannerImg;
    @SerializedName("header_img")
    @Expose
    private String headerImg;
    @SerializedName("icon_img")
    @Expose
    private String iconImg;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description_html")
    @Expose
    private String descriptionHtml;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("comment_score_hide_mins")
    @Expose
    private int commentScore;
    @SerializedName("created_utc")
    @Expose
    private long createdUtc;

    public InnerData(String title, String iconImg, String description) {
        this.title = title;
        this.iconImg = iconImg;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPublicDescription() {
        return publicDescription;
    }

    public void setPublicDescription(String publicDescription) {
        this.publicDescription = publicDescription;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(int commentScore) {
        this.commentScore = commentScore;
    }

    public long getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(long createdUtc) {
        this.createdUtc = createdUtc;
    }
}
