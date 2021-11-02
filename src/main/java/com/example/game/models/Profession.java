package com.example.game.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Profession {
    WARRIOR("Warrior"),
    ROGUE("Rogue"),
    SORCERER("Sorcerer"),
    CLERIC("Cleric"),
    PALADIN("Paladin"),
    NAZGUL("Nazgul"),
    WARLOCK("Warlock"),
    DRUID("Druid");

    private final String name;


    Profession(String name) {
        this.name = name;
    }
    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static Profession getDepartmentFromCode(String value) {

        for (Profession dep : Profession.values()) {

            if (dep.getName().equals(value)) {

                return dep;
            }
        }

        return null;
    }
}
