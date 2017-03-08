package com.gio.producthunt_client.common.eventbus.events;

/**
 * Created by Alexey Mitutov on 23.12.2016.
 * ИТЛ Консалтинг
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
