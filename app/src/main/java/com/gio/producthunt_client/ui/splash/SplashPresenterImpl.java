package com.gio.producthunt_client.ui.splash;

import android.os.Handler;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by Gigauri
 * gio
 */

public class SplashPresenterImpl implements SplashPresenter {

    private SplashView view;

    @Inject
    public SplashPresenterImpl(SplashView view) {
        this.view = view;
    }

    @Override
    public Subscription subscribeToBus(Bus bus, Gson gson) {
        return bus.observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    if (event instanceof CategoriesEvent) {
                        Type categoriesListType = new TypeToken<List<Category>>() {
                        }.getType();
                        List<Category> categoryList = ((CategoriesEvent) event).getCategoryList();

                    } else if (event instanceof ThrowableEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    } else if (event instanceof HttpErrorEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    }
                });
    }

    @Override
    public void onCreate(NetworkService networkService, Bus bus) {
        Observable<Response<CategoriesEvent>> responseObservable = networkService.getCategories(Config.ACCESS_TOKEN);
        responseObservable.compose(RxUtil.applySchedulersAndRetry())
                .subscribe(response -> {

                    int responseCode = response.code();

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        CategoriesEvent categoriesEvent = response.body();
                        bus.send(new CategoriesEvent(categoriesEvent.getCategoryList()));
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    bus.send(new ThrowableEvent(throwable));
                });



     /*   Observable<List<Category>> responseObservableReserved = networkService.getCategories(Config.ACCESS_TOKEN).compose(RxUtil.applySchedulersAndRetry());
        Observable<List<Post>> responseObservableSupply = networkService.getPosts(Config.ACCESS_TOKEN).compose(RxUtil.applySchedulersAndRetry());

        Observable.zip(responseObservableReserved, responseObservableSupply, ReservedAndSupplyLoadedEvent::new)
                .subscribe(bus::send, throwable -> {
                    throwable.printStackTrace();
                    bus.send(new ThrowableEvent(throwable));
                });
*/
        // запускаем соответствующую активити после задержки

        new Handler().postDelayed(() -> {
            {
                view.startMain();
            }
            view.finishActivity();
        }, Config.SHOW_SPLASH_DELAY_MILLIS);
    }

    private void startMainWithDelay(){

    }
}
