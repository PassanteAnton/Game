package com.example.game.repo.requests;

import com.example.game.exeption.NotValidDateException;
import com.example.game.models.Profession;
import com.example.game.models.Race;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UpdateRequest {
    @Size(max = 12)
    private String name;
    @Size(max = 30)
    private String title;

    private Race race;
    private Profession profession;

    private Long birthday;
    private boolean banned;
    @Min(0)
    @Max(10000000)
    private Integer experience;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Set getSetChanges() {
        Set <String> changes = new HashSet<>();
        if(name != null) changes.add("name");
        if(title != null) changes.add("title");
        if(race != null) changes.add("race");
        if(profession != null) changes.add("profession");
        if(experience != null) changes.add("experience");
        if(birthday != null) changes.add("birthday");

        return changes;
    }
}
