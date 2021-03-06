package com.gio.producthunt_client.network;

import com.gio.producthunt_client.model.CategoryResponse;
import com.gio.producthunt_client.model.PostResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */

public interface NetworkService {

    @GET("v1/categories")
    Observable<Response<CategoryResponse>> getCategories(@Query("access_token") String token);

    @GET("v1/categories/{category}/posts")
    Observable<Response<PostResponse>> getPosts(@Path("category") String category, @Query("access_token") String token);
}
