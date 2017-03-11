package com.gio.producthunt_client.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.app.BaseActivity;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.di.HasComponent;
import com.gio.producthunt_client.di.components.DaggerSplashComponent;
import com.gio.producthunt_client.di.components.ProductHuntAppComponent;
import com.gio.producthunt_client.di.components.SplashComponent;
import com.gio.producthunt_client.di.modules.SplashModule;
import com.gio.producthunt_client.ui.main.MainActivity;

import javax.inject.Inject;

import rx.Subscription;

public class SplashActivity extends BaseActivity implements HasComponent<SplashComponent>, SplashView {

    @Inject
    public SplashPresenterImpl presenter;

    private SplashComponent component;

    private Subscription eventSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter.onCreate(networkService, bus, preferences);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventSubscription = presenter.subscribeToBus(bus, gson);
    }

    @Override
    protected void onPause() {
        if (eventSubscription != null && !eventSubscription.isUnsubscribed())
            eventSubscription.unsubscribe();
        super.onPause();
    }

    //=======--------- SplashView impelement metod START ---------=========


    @Override
    public void startMain(String jsonCategories) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Categories", jsonCategories);
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
        // включаем анимацию при закрытии заставки - исчезновение
        //overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
        overridePendingTransition(R.anim.fade_in_activity, R.anim.fade_out_activity);
    }

    @Override
    public void showMessage(int message, @MessageType int type) {
        showToast(message, type);
    }

    //=======--------- SplashView impelement metod END ---------=========

    // BaseActivity extended method =========
    @Override
    protected void setupComponent(ProductHuntAppComponent appComponent) {
        component = DaggerSplashComponent.builder()
                .productHuntAppComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build();
        component.inject(this);
    }

    // HasComponent implement method =========
    @Override
    public SplashComponent getComponent() {
        return component;
    }
}
