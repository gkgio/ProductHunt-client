package com.gio.producthunt_client.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gio.producthunt_client.R;
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
        return categoryList.size();
    }

    @Override
    public Category getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_header_spinner, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.spinnerItem);
        textView.setText(categoryList.get(position).getName());

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_header_spinner, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.spinnerItem);
        textView.setText(categoryList.get(position).getName());

        return view;
    }
}
