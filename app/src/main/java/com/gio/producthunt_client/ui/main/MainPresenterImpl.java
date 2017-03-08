package com.gio.producthunt_client.ui.main;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.common.eventbus.events.HttpErrorEvent;
import com.gio.producthunt_client.common.eventbus.events.ThrowableEvent;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    @Inject
    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public Subscription subscribeToBus(Bus bus) {
        return bus.observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    view.hideProgress();
                    if (event instanceof ThrowableEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    } else if (event instanceof HttpErrorEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    }
                });
    }
}
