package com.gio.producthunt_client.common.eventbus.events.splash;

import com.gio.producthunt_client.model.Category;

import java.util.List;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */

public class CategoriesLoadEvent {
    private final List<Category> categoryList;

    public CategoriesLoadEvent(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
