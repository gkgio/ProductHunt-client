package com.gio.producthunt_client.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.di.components.ProductHuntAppComponent;
import com.gio.producthunt_client.network.NetworkService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * Created by Gio
 * GKGIO
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    public Gson gson;
    @Inject
    public SharedPreferences preferences;
    @Inject
    public Bus bus;
    @Inject @Named("cached")
    public NetworkService cachedNetworkService;
    @Inject @Named("no_cached")
    public NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(ProductHuntApp.get(this).getAppComponent());
    }

    public void showToast(int message, @MessageType int type) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        toastView.setBackgroundResource(type == MessageType.ERROR ? R.drawable.toast_error_bg : R.drawable.toast_info_bg);
        toast.show();
    }

    protected abstract void setupComponent(ProductHuntAppComponent appComponent);
}
