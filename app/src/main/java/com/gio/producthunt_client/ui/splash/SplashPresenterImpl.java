package com.gio.producthunt_client.ui.splash;

import android.content.SharedPreferences;
import android.os.Handler;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.common.Config;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.common.eventbus.events.HttpErrorEvent;
import com.gio.producthunt_client.common.eventbus.events.ThrowableEvent;
import com.gio.producthunt_client.common.eventbus.events.splash.CategoriesLoadEvent;
import com.gio.producthunt_client.common.rx.RxUtil;
import com.gio.producthunt_client.model.Category;
import com.gio.producthunt_client.model.CategoryResponse;
import com.gio.producthunt_client.network.NetworkService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
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
                    if (event instanceof CategoriesLoadEvent) {
                        Type categoriesListType = new TypeToken<List<Category>>() {
                        }.getType();
                        List<Category> categoryList = ((CategoriesLoadEvent) event).getCategoryList();
                        startMainWithDelay(gson.toJson(categoryList, categoriesListType));
                    } else if (event instanceof ThrowableEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    } else if (event instanceof HttpErrorEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    }
                });
    }

    @Override
    public void onCreate(NetworkService networkService, Bus bus, SharedPreferences preferences, Realm realm) {
        preferences.edit().putString(Config.apiURL, "https://www.producthunt.com/")
                .apply();

        Observable<Response<CategoryResponse>> responseObservable = networkService.getCategories(Config.ACCESS_TOKEN);
        responseObservable.compose(RxUtil.applySchedulersAndRetry())
                .subscribe(response -> {

                    int responseCode = response.code();

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        CategoryResponse categoryResponse = response.body();
                        realm.executeTransaction(transaction -> transaction.copyToRealmOrUpdate(categoryResponse));
                        bus.send(new CategoriesLoadEvent(categoryResponse.getCategories()));
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    bus.send(new ThrowableEvent(throwable));
                });
    }

    private void startMainWithDelay(String jsonCategories) {
        // запускаем соответствующую активити после задержки
        new Handler().postDelayed(() -> {
            {
                view.startMain(jsonCategories);
            }
            view.finishActivity();
        }, Config.SHOW_SPLASH_DELAY_MILLIS);
    }
}
