package com.gio.producthunt_client.common.eventbus.events.main;

import com.gio.producthunt_client.model.Post;

/**
 * Created by Георгий on 11.03.2017.
 * gio.com
 */

public class OpenPageEvent {

    final private Post post;

    public OpenPageEvent(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
