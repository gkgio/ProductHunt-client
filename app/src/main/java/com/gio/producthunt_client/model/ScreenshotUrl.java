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
public class ScreenshotUrl implements Serializable{

    private String _300px;

    private String _850px;
}
