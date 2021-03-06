package com.gio.producthunt_client.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Георгий on 08.03.2017.
 * gio.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Category extends RealmObject implements Serializable {
    @PrimaryKey
    private Integer id;

    private String slug;

    private String name;

    private String color;

    private String itemName;
}
