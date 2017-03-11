package com.gio.producthunt_client.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.app.BaseActivity;
import com.gio.producthunt_client.common.adapters.CategoryAdapter;
import com.gio.producthunt_client.common.adapters.PageListRecyclerAdapter;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.di.HasComponent;
import com.gio.producthunt_client.di.components.DaggerMainComponent;
import com.gio.producthunt_client.di.components.MainComponent;
import com.gio.producthunt_client.di.components.ProductHuntAppComponent;
import com.gio.producthunt_client.di.modules.MainModule;
import com.gio.producthunt_client.model.Category;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxbinding.widget.RxAdapterView;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import okhttp3.Cache;
import rx.Subscription;

public class MainActivity extends BaseActivity implements HasComponent<MainComponent>, MainView{
    private Realm realm;

    private Toolbar toolbar;
    private ProgressBar progressBar;

    private Subscription eventSubscription;

    private Spinner spinner;

    private final int REQUEST_CODE = 1;

    private PageListRecyclerAdapter pageListRecyclerAdapter;
    private CategoryAdapter categoryAdapter;

    @Inject
    MainPresenterImpl presenter;
    @Inject
    Cache cache;

    private MainComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        spinner = (Spinner)findViewById(R.id.toolbar_spinner);
        RecyclerView rvPosts = (RecyclerView)findViewById(R.id.rvPosts);

        final List<Category> categoryList = gson.fromJson(getIntent().getStringExtra("Categories"), new TypeToken<List<Category>>() {
        }.getType());

        pageListRecyclerAdapter = new PageListRecyclerAdapter(this);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        rvPosts.setAdapter(pageListRecyclerAdapter);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        spinner.setAdapter(categoryAdapter);
        RxAdapterView.itemSelections(spinner)
                .filter(integer -> categoryAdapter.getCount() != 0)
                .subscribe(integer -> {
                    Category category = categoryAdapter.getItem(integer);
                    presenter.onSpinnerItemSelected(category, getApplicationContext());
                    invalidateOptionsMenu();
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_main_update)
            //presenter.updateTabContent(preferences, bus, cachedNetworkService, cache, realm);

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        realm = Realm.getDefaultInstance();
//        presenter.onCreate(networkService,bus);
        super.onResume();
        eventSubscription = presenter.subscribeToBus(bus);
    }

    @Override
    protected void onPause() {
        if(!realm.isEmpty()) {
            realm.close();
        }
        if (eventSubscription != null && !eventSubscription.isUnsubscribed())
            eventSubscription.unsubscribe();
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // обновляем контент в табах по возврату из любой активити
       // if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) presenter.updateContent(preferences, bus, cachedNetworkService, cache, realm);
    }

    //=======--------- MainView impelement metod START ---------=========

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(int message, @MessageType int type) {
        showToast(message, type);
    }


    //=======--------- MainView impelement metod END -----------=========


    // BaseActivity extended method =========
    @Override
    protected void setupComponent(ProductHuntAppComponent appComponent) {
        component = DaggerMainComponent.builder()
                .productHuntAppComponent(appComponent)
                .mainModule(new MainModule(this))
                .build();
        component.inject(this);
    }

    // HasComponent implement method =========
    @Override
    public MainComponent getComponent() {
        return component;
    }

}
