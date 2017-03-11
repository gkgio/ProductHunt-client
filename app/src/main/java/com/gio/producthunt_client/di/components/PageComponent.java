package com.gio.producthunt_client.di.components;

import com.gio.producthunt_client.di.ActivityScope;
import com.gio.producthunt_client.di.modules.PageModule;
import com.gio.producthunt_client.ui.page.PageActivity;

import dagger.Component;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */

@ActivityScope
@Component(
        dependencies = ProductHuntAppComponent.class,
        modules = PageModule.class
)
public interface PageComponent {
    void inject(PageActivity pageActivity);
}
