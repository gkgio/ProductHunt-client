package com.gio.producthunt_client.di.components;

import com.gio.producthunt_client.di.ActivityScope;
import com.gio.producthunt_client.di.modules.BrowserModule;
import com.gio.producthunt_client.ui.browser.BrowserActivity;

import dagger.Component;

/**
 * Created by Георгий on 11.03.2017.
 * gio.com
 */

@ActivityScope
@Component(
        dependencies = ProductHuntAppComponent.class,
        modules = BrowserModule.class
)
public interface BrowserComponent {
    void inject(BrowserActivity browserActivity);
}
