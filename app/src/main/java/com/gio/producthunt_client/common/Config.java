package com.gio.producthunt_client.common;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */

public class Config {
    public static final String apiURL = "https://api.producthunt.com/" ;
    public static final String apiPostsURL = "https://www.producthunt.com/posts/";

    // задержка при показе сплэш экрана в миллисекундах
    public static final int SHOW_SPLASH_DELAY_MILLIS = 1000;

    // имя файла для http кэша
    public static final String CACHE_FILE_NAME = "responseCache";
    // время хранения кэша в минутах
    public static final int CACHE_TIME = 1;
    // размер файла для кжша
    public static final long CACHE_FILE_SIZE = 10 * 1024 * 1024; // 10 Mb

    public static final String ACCESS_TOKEN = "591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff";

    public static final String CATEGORY = "undefined";
}
