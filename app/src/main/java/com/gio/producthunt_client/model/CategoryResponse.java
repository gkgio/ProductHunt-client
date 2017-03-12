package com.gio.producthunt_client.model;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Георгий on 11.03.2017.
 * gio.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CategoryResponse extends RealmObject implements Serializable {

    @PrimaryKey
    private long id = 1;

    private RealmList<Category> categories;
}
