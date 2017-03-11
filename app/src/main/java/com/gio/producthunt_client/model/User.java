package com.gio.producthunt_client.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User implements Serializable {

    private Integer id;

    private String createdAt;

    private String name;

    private String username;

    private String headline;

    private String twitterUsername;

    private String websiteUrl;

    private String profileUrl;

    private ImageUrl imageUrl;
}
