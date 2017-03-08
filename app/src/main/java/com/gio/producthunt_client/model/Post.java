package com.gio.producthunt_client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */

public class Post {

    private Integer categoryId;

    private String day;

    private Integer id;

    private String name;

    private String productState;

    private String tagline;

    private Object iosFeaturedAt;

    private Integer commentsCount;

    private String createdAt;

    private CurrentUser currentUser;

    private String discussionUrl;

    private Object exclusive;

    private Boolean featured;

    private Boolean makerInside;

    private List<Object> makers = null;

    private List<Object> platforms = null;

    private List<Topic> topics = null;

    private String redirectUrl;

    private ScreenshotUrl screenshotUrl;

    private Thumbnail thumbnail;

    private User user;

    private Integer votesCount;
}
