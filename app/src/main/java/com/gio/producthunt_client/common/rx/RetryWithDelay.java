package com.gio.producthunt_client.common.rx;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by GIo on 27.12.2016.
 * gkgio
 */
public class RetryWithDelay implements Func1<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    /**
     * @param maxRetries количество повторов
     * @param retryDelayMillis время между повторами в миллисекундах
     * */
    RetryWithDelay(final int maxRetries, final int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.retryCount = 0;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> attempts) {
        return attempts
                .flatMap(throwable -> {
                    if (++retryCount < maxRetries && (throwable instanceof ConnectException || throwable instanceof SocketTimeoutException)) {
                        return Observable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                    }else {
                        return Observable.error(throwable);
                    }
                });
    }
}
