package com.gio.producthunt_client.common;

import io.realm.RealmObject;
import lombok.Getter;

/**
 * Created by g.gigauri on 20.01.2017.
 * ИТЛ Консалтинг
 */

public class LongWrapper extends RealmObject {

    @Getter
    private long value;

    public LongWrapper(long value) {
        this.value = value;
    }

    public LongWrapper() {

    }
}
