package com.gio.producthunt_client.ui.browser;

import com.gio.producthunt_client.common.eventbus.Bus;

import rx.Subscription;

/**
 * Created by Георгий on 11.03.2017.
 * gio.com
 */

public interface BrowserPresenter {
    Subscription subscribeToBus(Bus bus);
}
