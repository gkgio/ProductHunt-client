package com.gio.producthunt_client.di.components;

import android.app.Application;
import android.content.SharedPreferences;

import com.gio.producthunt_client.app.ProductHuntApp;
import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.di.modules.ProductHuntAppModule;
import com.gio.producthunt_client.network.NetworkService;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */
@Singleton
@Component(modules = {ProductHuntAppModule.class})
public interface ProductHuntAppComponent {
    void inject(ProductHuntApp productHuntApp);

    Application app();

    SharedPreferences sharedPreferences();

    Bus eventBus();

    Gson gson();

    Cache cache();

    @Named("cached")
    NetworkService mobukCachedService();

    @Named("no_cached")
    NetworkService mobukNoCachedService();
}
