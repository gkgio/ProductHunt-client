package com.gio.producthunt_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CurrentUser {
    @SerializedName("voted_for_post")
    @Expose
    private Boolean votedForPost;

    @SerializedName("commented_on_post")
    @Expose
    private Boolean commentedOnPost;
}