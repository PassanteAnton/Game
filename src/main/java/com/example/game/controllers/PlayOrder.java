package com.example.game.controllers;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize()
public enum PlayOrder {
    ID("id"), // default
    NAME("name"),
    EXPERIENCE("experience"),
    BIRTHDAY("birthday"),
    LEVEL("level");

    private final String fieldName;

    PlayOrder(String fieldName) {
        this.fieldName = fieldName;
    }
    @JsonGetter
    public String getFieldName() {
        return fieldName;
    }


}
