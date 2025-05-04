package com.github.psinalberth.catalog.event.controllers;

import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@UtilityClass
public class ResponseExtensions {

    public URI toCreatedURI(Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
