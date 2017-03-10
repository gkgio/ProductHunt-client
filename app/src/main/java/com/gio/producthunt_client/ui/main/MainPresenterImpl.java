package com.gio.producthunt_client.ui.main;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.common.Config;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.common.eventbus.events.HttpErrorEvent;
import com.gio.producthunt_client.common.eventbus.events.ThrowableEvent;
import com.gio.producthunt_client.common.eventbus.events.splash.CategoriesEvent;
import com.gio.producthunt_client.common.rx.RxUtil;
import com.gio.producthunt_client.model.Category;
import com.gio.producthunt_client.network.NetworkService;

import java.net.HttpURLConnection;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
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

   /* @Override
    public void onCreate(NetworkService networkService, Bus bus) {
        Observable<Response<List<Category>>> responseObservable = networkService.getCategories(Config.ACCESS_TOKEN);
        responseObservable.compose(RxUtil.applySchedulersAndRetry())
                .subscribe(response -> {

                    int responseCode = response.code();

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        List<Category> categoryList = response.body();
                        bus.send(new CategoriesEvent(categoryList));
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    bus.send(new ThrowableEvent(throwable));
                });
    }*/
}
