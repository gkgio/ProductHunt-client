package com.gio.producthunt_client.ui.main;

import android.content.SharedPreferences;

import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.model.Category;
import com.gio.producthunt_client.network.NetworkService;
import com.google.gson.Gson;

import okhttp3.Cache;
import rx.Subscription;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */

public interface MainPresenter {
    Subscription subscribeToBus(Bus bus, Gson gson);
    void updateTabContent(SharedPreferences preferences, Bus bus, NetworkService service, Cache cache);
    void onSpinnerItemSelected(Category category, SharedPreferences preferences);
}
