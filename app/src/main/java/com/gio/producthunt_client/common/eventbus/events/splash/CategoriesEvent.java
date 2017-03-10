package com.gio.producthunt_client.common.eventbus.events.splash;

import com.gio.producthunt_client.model.Category;

import java.util.List;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */

public class CategoriesEvent {
    private final List<Category> categoryList;

    public CategoriesEvent(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
