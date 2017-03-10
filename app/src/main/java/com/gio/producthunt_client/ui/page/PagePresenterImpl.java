package com.gio.producthunt_client.ui.page;

import com.gio.producthunt_client.ui.main.MainView;

import javax.inject.Inject;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */

public class PagePresenterImpl implements PagePresenter {
    private PageView view;

    @Inject
    public PagePresenterImpl(PageView view) {
        this.view = view;
    }
}
