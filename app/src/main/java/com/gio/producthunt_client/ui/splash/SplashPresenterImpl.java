package com.gio.producthunt_client.ui.splash;

import android.os.Handler;

import com.gio.producthunt_client.common.Config;

import javax.inject.Inject;


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
    public void onCreate() {

        // запускаем соответствующую активити после задержки
        new Handler().postDelayed(() -> {
            view.startMain();
            view.finishActivity();
        }, Config.SHOW_SPLASH_DELAY_MILLIS);
    }
}
