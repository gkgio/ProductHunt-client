package com.gio.producthunt_client.di.modules;

import com.gio.producthunt_client.ui.splash.SplashActivity;
import com.gio.producthunt_client.ui.splash.SplashPresenter;
import com.gio.producthunt_client.ui.splash.SplashPresenterImpl;
import com.gio.producthunt_client.ui.splash.SplashView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Gigauri
 * gio
 */

@Module
public class SplashModule {

    private SplashActivity activity;

    public SplashModule(SplashActivity activity) {
        this.activity = activity;
    }

    /** Provide SplashView */
    @Provides
    SplashView provideSplashView() {
        return activity;
    }

    /** Provide SplashPresenterImpl */
    @Provides
    SplashPresenter provideSplashPresenterImpl(SplashView view) {
        return new SplashPresenterImpl(view);
    }
}
