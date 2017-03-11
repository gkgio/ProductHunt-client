package com.gio.producthunt_client.common.eventbus.events.main;

import com.gio.producthunt_client.model.Post;

import java.util.List;

/**
 * Created by Георгий on 11.03.2017.
 * gio.com
 */

public class PostLoadEvent {

    private final List<Post> postList;

    public PostLoadEvent(List<Post> postList) {
        this.postList = postList;
    }

    public List<Post> getPostList() {
        return postList;
    }
}
