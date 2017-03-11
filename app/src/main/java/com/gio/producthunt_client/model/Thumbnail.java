package com.gio.producthunt_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Thumbnail {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("media_type")
    @Expose
    private String mediaType;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
}
