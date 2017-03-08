package com.gio.producthunt_client.app;

import android.app.Application;
import android.content.Context;

import com.gio.producthunt_client.di.components.ProductHuntAppComponent;
import com.gio.producthunt_client.di.modules.ProductHuntAppModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */

public class ProductHuntApp extends Application {

    private ProductHuntAppComponent productHuntAppComponent;

    public static ProductHuntApp get(Context context) {
        return (ProductHuntApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraphAndInject();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public void buildObjectGraphAndInject() {
        productHuntAppComponent = DaggerProductHuntAppComponent.builder()
                .productHuntAppModule(new ProductHuntAppModule(this))
                .build();

    }

    public ProductHuntAppComponent getAppComponent() {
        return productHuntAppComponent;
    }
}
