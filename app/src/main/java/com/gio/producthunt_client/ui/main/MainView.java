package com.gio.producthunt_client.ui.main;

import com.gio.producthunt_client.common.enums.MessageType;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */

public interface MainView {
    void showProgress();
    void hideProgress();
    void showMessage(int message, @MessageType int type);
}
