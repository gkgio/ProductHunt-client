package com.gio.producthunt_client.ui.main;

import com.gio.producthunt_client.common.eventbus.Bus;

import rx.Subscription;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */

public interface MainPresenter {
    Subscription subscribeToBus(Bus bus);
}
