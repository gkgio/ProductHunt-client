package com.gio.producthunt_client.ui.splash;

import android.os.Handler;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.common.Config;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.common.eventbus.events.HttpErrorEvent;
import com.gio.producthunt_client.common.eventbus.events.ThrowableEvent;
import com.gio.producthunt_client.network.NetworkService;

import java.net.HttpURLConnection;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Gigauri
 * gio
 */

public class SplashPresenterImpl implements SplashPresenter {

    private SplashView view;

    @Inject
    public SplashPresenterImpl(SplashView view) {
        this.view = view;
    }

    @Override
    public Subscription subscribeToBus(Bus bus) {
        return bus.observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    if (event instanceof ThrowableEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    } else if (event instanceof HttpErrorEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    }
                });
    }

    @Override
    public void onCreate(NetworkService networkService, Bus bus) {
        Observable<Response<Void>> responseObservable = networkService.isBlockedProfile(profileId);
        responseObservable.subscribeOn(Schedulers.io())
                .subscribe(response -> {
                    if (response.code() == HttpURLConnection.HTTP_FORBIDDEN)

                }, throwable -> {});

        // запускаем соответствующую активити после задержки
        new Handler().postDelayed(() -> {
            view.startMain();
            view.finishActivity();
        }, Config.SHOW_SPLASH_DELAY_MILLIS);
    }
}
