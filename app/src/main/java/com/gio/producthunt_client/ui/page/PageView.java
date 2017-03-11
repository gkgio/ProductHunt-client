package com.gio.producthunt_client.ui.page;

import com.gio.producthunt_client.common.enums.MessageType;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */

public interface PageView {
    void showProgress();
    void hideProgress();
    void showMessage(int message, @MessageType int type);
}
