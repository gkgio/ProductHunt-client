package com.gio.producthunt_client.common.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gio.producthunt_client.model.Category;

import java.util.List;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Category getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
