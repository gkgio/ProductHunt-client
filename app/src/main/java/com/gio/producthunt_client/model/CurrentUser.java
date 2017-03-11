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
public class CurrentUser implements Serializable{

    private Boolean votedForPost;

    private Boolean commentedOnPost;
}