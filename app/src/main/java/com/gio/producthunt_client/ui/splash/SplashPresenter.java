package com.gio.producthunt_client.ui.splash;

import android.content.SharedPreferences;

import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.network.NetworkService;
import com.google.gson.Gson;

import rx.Subscription;

/**
 * Created by Gigauri
 * gio
 */

public interface SplashPresenter {
    void onCreate(NetworkService networkService, Bus bus, SharedPreferences preferences);
    Subscription subscribeToBus(Bus bus, Gson gson);
}
