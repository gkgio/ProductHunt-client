package com.gio.producthunt_client.common.eventbus;

import javax.inject.Inject;

import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * Created by Alexey Mitutov on 05.12.2016.
 * ИТЛ Консалтинг
 */

public class Bus extends SerializedSubject<Object, Object> {

    @Inject
    public Bus() {
        super(PublishSubject.create());
    }

    public void send(Object o) {
        this.onNext(o);
    }
}
