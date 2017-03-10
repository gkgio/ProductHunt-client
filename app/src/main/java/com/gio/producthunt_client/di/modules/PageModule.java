package com.gio.producthunt_client.di.modules;

import com.gio.producthunt_client.ui.page.PageActivity;
import com.gio.producthunt_client.ui.page.PagePresenter;
import com.gio.producthunt_client.ui.page.PagePresenterImpl;
import com.gio.producthunt_client.ui.page.PageView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */
@Module
public class PageModule {
    private PageActivity activity;

    public PageModule(PageActivity activity) {
        this.activity = activity;
    }

    /** Provide PageView */
    @Provides
    PageView providePageView() {
        return activity;
    }

    /** Provide PagePresenterImpl */
    @Provides
    PagePresenter providePagePresenterImpl(PageView view) {
        return new PagePresenterImpl(view);
    }
}
