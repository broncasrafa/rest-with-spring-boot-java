package com.rsfrancisco.restwithspringbootjava.domain.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class UrlUtil {
    public static URI getURI(String path, Object id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(id).toUri();
        return uri;
    }
}
