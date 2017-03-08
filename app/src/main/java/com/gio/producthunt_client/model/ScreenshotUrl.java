package com.gio.producthunt_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ScreenshotUrl {
    @SerializedName("300px")
    @Expose
    private String _300px;
    @SerializedName("850px")
    @Expose
    private String _850px;
}
