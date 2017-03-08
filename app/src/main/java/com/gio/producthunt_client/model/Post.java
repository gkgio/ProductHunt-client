package com.gio.producthunt_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */
@Getter
@Setter
@RequiredArgsConstructor
public class Post {

    @SerializedName("category_id")
    @Expose
    private Integer categoryId;

    @SerializedName("day")
    @Expose
    private String day;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("product_state")
    @Expose
    private String productState;

    @SerializedName("tagline")
    @Expose
    private String tagline;

    @SerializedName("ios_featured_at")
    @Expose
    private Object iosFeaturedAt;

    @SerializedName("comments_count")
    @Expose
    private Integer commentsCount;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("current_user")
    @Expose
    private CurrentUser currentUser;

    @SerializedName("discussion_url")
    @Expose
    private String discussionUrl;

    @SerializedName("exclusive")
    @Expose
    private Object exclusive;

    @SerializedName("featured")
    @Expose
    private Boolean featured;

    @SerializedName("maker_inside")
    @Expose
    private Boolean makerInside;

    @SerializedName("makers")
    @Expose
    private List<Object> makers = null;

    @SerializedName("platforms")
    @Expose
    private List<Object> platforms = null;

    @SerializedName("topics")
    @Expose
    private List<Topic> topics = null;

    @SerializedName("redirect_url")
    @Expose
    private String redirectUrl;

    @SerializedName("screenshot_url")
    @Expose
    private ScreenshotUrl screenshotUrl;

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("votes_count")
    @Expose
    private Integer votesCount;
}
