package com.gio.producthunt_client.ui.splash;

import com.gio.producthunt_client.common.enums.MessageType;

/**
 * Created by Gigauri
 * gio
 */

public interface SplashView {
    void startMain(String jsonCategories);
    void finishActivity();
    void showMessage(int message, @MessageType int type);
}
