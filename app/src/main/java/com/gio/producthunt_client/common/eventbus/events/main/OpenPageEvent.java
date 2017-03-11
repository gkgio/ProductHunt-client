package com.gio.producthunt_client.common.eventbus.events.main;

/**
 * Created by Георгий on 11.03.2017.
 * gio.com
 */

public class OpenPageEvent {

    final private int postId;

    public OpenPageEvent(int postId) {
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }
}
