package com.gio.producthunt_client.common.eventbus.events;

/**
 * Created by gio on 23.12.2016.
 * gkgio
 */

public class ThrowableEvent {

    private final Throwable trowable;

    public ThrowableEvent(Throwable trowable) {
        this.trowable = trowable;
    }

    public Throwable getTrowable() {
        return trowable;
    }
}
