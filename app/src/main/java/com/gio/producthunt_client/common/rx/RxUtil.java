package com.gio.producthunt_client.common.rx;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Alexey Mitutov on 10.08.16.
 * Copyright (c) 2016 Bestcon.
 */
public class RxUtil {

    @NonNull
    public static <T> Observable.Transformer<T, T> applySchedulersAndRetry() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 2000));
    }
}
