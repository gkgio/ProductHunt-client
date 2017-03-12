package com.gio.producthunt_client.ui.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gio.producthunt_client.R;
import com.gio.producthunt_client.app.BaseActivity;
import com.gio.producthunt_client.common.Config;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.di.HasComponent;
import com.gio.producthunt_client.di.components.DaggerPageComponent;
import com.gio.producthunt_client.di.components.PageComponent;
import com.gio.producthunt_client.di.components.ProductHuntAppComponent;
import com.gio.producthunt_client.di.modules.PageModule;
import com.gio.producthunt_client.model.Post;
import com.gio.producthunt_client.ui.browser.BrowserActivity;
import com.jakewharton.rxbinding.view.RxView;

import javax.inject.Inject;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */

public class PageActivity extends BaseActivity implements HasComponent<PageComponent>, PageView {
    private Toolbar toolbar;

    @Inject
    PagePresenterImpl presenter;
    private PageComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        // toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }

        final Post post = gson.fromJson(getIntent().getStringExtra("PostObject"), Post.class);

        Button btnOpenWeb = (Button) findViewById(R.id.btnOpenWeb);
        TextView tvPostTitle = (TextView) findViewById(R.id.tvTitlePost);
        TextView tvPostDescription = (TextView) findViewById(R.id.tvDescPost);
        TextView tvPostVotes = (TextView) findViewById(R.id.tvVotes);
        ImageView ivPost = (ImageView) findViewById(R.id.ivBarPost);

        tvPostTitle.setText(post.getName());
        tvPostDescription.setText(post.getTagline());
        tvPostVotes.setText(String.valueOf(post.getVotesCount()));

        // отображаем каритнку
        Glide.with(this)
                .load(post.getThumbnail().getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_default_image)
                .crossFade()
                .into(ivPost);

        RxView.clicks(btnOpenWeb)
                .subscribe(aVoid -> {
                    startBrowserActivity(post.getId().toString());
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toolbar.setTitle("");
    }

    private void startBrowserActivity(String postId) {
        Intent intent = new Intent(this, BrowserActivity.class);
        intent.putExtra("postId", postId);
        intent.putExtra("url", Config.apiPostsURL);
        startActivity(intent);
    }

    //=======--------- MainView impelement metod START ---------=========


    @Override
    public void showMessage(int message, @MessageType int type) {
        showToast(message, type);
    }


    //=======--------- MainView impelement metod END -----------=========


    // BaseActivity extended method =========
    @Override
    protected void setupComponent(ProductHuntAppComponent appComponent) {
        component = DaggerPageComponent.builder()
                .productHuntAppComponent(appComponent)
                .pageModule(new PageModule(this))
                .build();
        component.inject(this);
    }

    // HasComponent implement method =========
    @Override
    public PageComponent getComponent() {
        return component;
    }
}
