package com.gio.producthunt_client.ui.main;

import com.gio.producthunt_client.common.enums.MessageType;
import com.gio.producthunt_client.model.Post;

import java.util.List;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */

public interface MainView {
    void showProgress();
    void hideProgress();
    void showMessage(int message, @MessageType int type);
    void startPageActivity(String jsonPost);
    void updatePosts(List<Post> postList);
}
