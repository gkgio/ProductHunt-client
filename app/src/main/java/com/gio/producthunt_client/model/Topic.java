package com.gio.producthunt_client.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Topic {

    private Integer id;

    private String name;

    private String slug;
}
