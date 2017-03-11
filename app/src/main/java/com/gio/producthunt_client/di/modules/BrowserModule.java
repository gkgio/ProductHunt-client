package com.gio.producthunt_client.di.modules;

import com.gio.producthunt_client.ui.browser.BrowserActivity;
import com.gio.producthunt_client.ui.browser.BrowserPresenter;
import com.gio.producthunt_client.ui.browser.BrowserPresenterImpl;
import com.gio.producthunt_client.ui.browser.BrowserView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Георгий on 11.03.2017.
 * gio.com
 */

@Module
public class BrowserModule {
    private BrowserActivity activity;

    public BrowserModule(BrowserActivity activity) {
        this.activity = activity;
    }

    /** Provide BrowserView */
    @Provides
    BrowserView provideBrowserView() {
        return activity;
    }

    /** Provide BrowserPresenterImpl */
    @Provides
    BrowserPresenter provideBrowserPresenterImpl(BrowserView view) {
        return new BrowserPresenterImpl(view);
    }
}
