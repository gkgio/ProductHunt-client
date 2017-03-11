package com.gio.producthunt_client.ui.main;

import android.content.SharedPreferences;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.common.Config;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.common.eventbus.events.HttpErrorEvent;
import com.gio.producthunt_client.common.eventbus.events.ThrowableEvent;
import com.gio.producthunt_client.common.eventbus.events.main.OpenPageEvent;
import com.gio.producthunt_client.common.eventbus.events.main.PostLoadEvent;
import com.gio.producthunt_client.common.rx.RxUtil;
import com.gio.producthunt_client.model.Category;
import com.gio.producthunt_client.model.Post;
import com.gio.producthunt_client.model.PostResponse;
import com.gio.producthunt_client.network.NetworkService;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Cache;
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
    public Subscription subscribeToBus(Bus bus, Gson gson) {
        return bus.observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    view.hideProgress();
                    if (event instanceof ThrowableEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    } else if (event instanceof HttpErrorEvent) {
                        view.showMessage(R.string.toast_error, MessageType.ERROR);
                    } else if (event instanceof OpenPageEvent) {
                        final Post post = ((OpenPageEvent) event).getPost();
                        view.startPageActivity(gson.toJson(((SellerEvent) event).getSeller(), Seller.class);
                    } else if (event instanceof PostLoadEvent) {
                        final List<Post> postList = ((PostLoadEvent) event).getPostList();
                        view.updatePosts(postList);
                    }
                });
    }

    @Override
    public void updateTabContent(SharedPreferences preferences, Bus bus, NetworkService service, Cache cache) {
        try {
            view.showProgress();
            // чистим кэш
            cache.evictAll();

            final String categoryName = preferences.getString(Config.CATEGORY, "undefined");

            Observable<Response<PostResponse>> responseObservable = service.getPosts(categoryName, Config.ACCESS_TOKEN);
            responseObservable.compose(RxUtil.applySchedulersAndRetry())
                    .subscribe(response -> {

                        int responseCode = response.code();

                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            PostResponse postResponse = response.body();
                            bus.send(new PostLoadEvent(postResponse.getPosts()));
                        }
                    }, throwable -> {
                        throwable.printStackTrace();
                        bus.send(new ThrowableEvent(throwable));
                    });

        } catch (IOException e) {
            e.printStackTrace();
            bus.send(new ThrowableEvent(e.getCause()));
        }

    }

    @Override
    public void onSpinnerItemSelected(Category category, SharedPreferences preferences) {
        preferences.edit().putString(Config.apiURL, "https://www.producthunt.com/posts/")
                .apply();

        preferences.edit().putString(Config.CATEGORY, category.getName().toLowerCase())
                .apply();
    }
}
