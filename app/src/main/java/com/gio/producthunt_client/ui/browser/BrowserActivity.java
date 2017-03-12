package com.gio.producthunt_client.ui.browser;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.app.BaseActivity;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.di.HasComponent;
import com.gio.producthunt_client.di.components.BrowserComponent;
import com.gio.producthunt_client.di.components.DaggerBrowserComponent;
import com.gio.producthunt_client.di.components.ProductHuntAppComponent;
import com.gio.producthunt_client.di.modules.BrowserModule;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Георгий on 11.03.2017.
 * gio.com
 */

public class BrowserActivity extends BaseActivity implements HasComponent<BrowserComponent>, BrowserView {

    private Toolbar toolbar;
    private ProgressBar progressBar;

    private Subscription eventSubscription;

    @Inject
    BrowserPresenterImpl presenter;

    private BrowserComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        // toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        eventSubscription = presenter.subscribeToBus(bus);
    }

    @Override
    protected void onPause() {
        if (eventSubscription != null && !eventSubscription.isUnsubscribed())
            eventSubscription.unsubscribe();
        super.onPause();
    }

    //=======--------- MainView impelement metod START ---------=========

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(int message, @MessageType int type) {
        showToast(message, type);
    }


    //=======--------- MainView impelement metod END -----------=========


    // BaseActivity extended method =========
    @Override
    protected void setupComponent(ProductHuntAppComponent appComponent) {
        component = DaggerBrowserComponent.builder()
                .productHuntAppComponent(appComponent)
                .browserModule(new BrowserModule(this))
                .build();
        component.inject(this);
    }

    // HasComponent implement method =========
    @Override
    public BrowserComponent getComponent() {
        return component;
    }
}
