package com.gio.producthunt_client.model;

import java.io.Serializable;
import java.util.List;

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
public class CategoryResponse implements Serializable{

    private List<Category> categories;
}
