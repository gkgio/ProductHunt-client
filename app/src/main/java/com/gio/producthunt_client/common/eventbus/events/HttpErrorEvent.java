package com.gio.producthunt_client.common.eventbus.events;

/**
 * Created by gio on 23.12.2016.
 * gio
 */

public class HttpErrorEvent {

    private final int code;

    public HttpErrorEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
