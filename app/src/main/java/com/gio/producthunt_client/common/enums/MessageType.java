package com.gio.producthunt_client.common.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Alexey Mitutov on 20.01.2017.
 * ИТЛ Консалтинг
 * Тип сообщения
 */

@IntDef({MessageType.INFO, MessageType.ERROR})
@Retention(RetentionPolicy.SOURCE)
public @interface MessageType {
    int INFO = 0;
    int ERROR = 1;
}
