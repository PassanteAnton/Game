package com.example.game.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Race {
    HUMAN("Human"),
    DWARF("Dwarf"),
    ELF("Elf"),
    GIANT("Giant"),
    ORC("Orc"),
    TROLL("Troll"),
    HOBBIT("Hobbit");

    private final String name;


    Race(String name) {
        this.name = name;
    }
    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  name;
    }

    @JsonCreator
    public static Race getDepartmentFromCode(String value) {

        for (Race dep : Race.values()) {

            if (dep.getName().equals(value)) {

                return dep;
            }
        }

        return null;
    }

}
