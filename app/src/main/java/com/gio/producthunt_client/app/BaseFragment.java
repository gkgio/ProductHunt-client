package com.gio.producthunt_client.app;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.di.HasComponent;
import com.gio.producthunt_client.network.NetworkService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;

public class BaseFragment extends Fragment {

    @Inject
    public Gson gson;
    @Inject
    public SharedPreferences preferences;
    @Inject
    public Bus bus;
    @Inject @Named("no_cached")
    public NetworkService networkService;
    @Inject @Named("cached")
    public NetworkService cachedNetworkService;

    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((HasComponent<T>)getActivity()).getComponent());
    }

    public void showToast(int message, @MessageType int type) {
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        toastView.setBackgroundResource(type == MessageType.ERROR ? R.drawable.toast_error_bg : R.drawable.toast_info_bg);
        toast.show();
    }
}
