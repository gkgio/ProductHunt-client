package com.gio.producthunt_client.di.components;

import com.gio.producthunt_client.di.ActivityScope;
import com.gio.producthunt_client.di.modules.SplashModule;
import com.gio.producthunt_client.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by Gigauri
 * gio
 */

@ActivityScope
@Component(
        dependencies = ProductHuntAppComponent.class,
        modules = SplashModule.class
)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
