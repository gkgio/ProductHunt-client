package com.gio.producthunt_client.ui.browser;

import com.gio.producthunt_client.common.enums.MessageType;

/**
 * Created by Георгий on 11.03.2017.
 * gio.com
 */

public interface BrowserView {
    void showProgress();
    void hideProgress();
    void showMessage(int message, @MessageType int type);
    void loadBrowserPage(String path);
}
