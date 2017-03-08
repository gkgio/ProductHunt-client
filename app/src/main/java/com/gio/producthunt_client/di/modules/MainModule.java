package com.gio.producthunt_client.di.modules;

import com.gio.producthunt_client.ui.main.MainActivity;
import com.gio.producthunt_client.ui.main.MainPresenter;
import com.gio.producthunt_client.ui.main.MainPresenterImpl;
import com.gio.producthunt_client.ui.main.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */
@Module
public class MainModule {
    private MainActivity activity;

    public MainModule(MainActivity activity) {
        this.activity = activity;
    }

    /** Provide MainView */
    @Provides
    MainView provideMainView() {
        return activity;
    }

    /** Provide MainPresenterImpl */
    @Provides
    MainPresenter provideMainPresenterImpl(MainView view) {
        return new MainPresenterImpl(view);
    }
}
