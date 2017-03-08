package com.gio.producthunt_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class User {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("headline")
    @Expose
    private String headline;

    @SerializedName("twitter_username")
    @Expose
    private String twitterUsername;

    @SerializedName("website_url")
    @Expose
    private String websiteUrl;

    @SerializedName("profile_url")
    @Expose
    private String profileUrl;

    @SerializedName("image_url")
    @Expose
    private ImageUrl imageUrl;
}
