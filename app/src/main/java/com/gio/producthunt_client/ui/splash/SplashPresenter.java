package com.gio.producthunt_client.ui.splash;

import android.content.SharedPreferences;

import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.network.NetworkService;
import com.google.gson.Gson;

import io.realm.Realm;
import rx.Subscription;

/**
 * Created by Gigauri
 * gio
 */

public interface SplashPresenter {
    void onCreate(NetworkService networkService, Bus bus, SharedPreferences preferences, Realm realm);
    Subscription subscribeToBus(Bus bus, Gson gson);
}
