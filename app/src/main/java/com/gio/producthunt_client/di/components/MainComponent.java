package com.gio.producthunt_client.di.components;

import com.gio.producthunt_client.di.ActivityScope;
import com.gio.producthunt_client.di.modules.MainModule;
import com.gio.producthunt_client.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Alexey Mitutov on 20.12.2016.
 * ИТЛ Консалтинг
 */

@ActivityScope
@Component(
        dependencies = ProductHuntAppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
