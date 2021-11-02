package com.example.game.service;

import com.example.game.exeption.NotValidDateException;
import com.example.game.models.Player;
import com.example.game.repo.PlayersRepo;
import com.example.game.repo.requests.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.util.Date;
import java.util.Set;

@Service
public class PlayerService {
    @Autowired
    private PlayersRepo playersRepo;

    public void create(Player player) throws NotValidDateException{

        if(!isValidDate(player.getBirthday().getTime())){
            throw new NotValidDateException();
        }
        player.setLevel(сalculateLvl(player.getExperience()));
        player.setUntilNextLevel(calculateUnitForNextLvl (player.getLevel() , player.getExperience()));
        playersRepo.save(player);
    }
    public Player update(Player player, UpdateRequest request) throws NotValidDateException{
        Set<String> changes = request.getSetChanges();
        if(changes.contains("birthday")){
            if(!isValidDate(request.getBirthday()))
            throw new NotValidDateException();
            else player.setBirthday(new Date(request.getBirthday()));
        }
        if(changes.contains("name")) player.setName(request.getName());
        if(changes.contains("title")) player.setTitle(request.getTitle());
        if(changes.contains("race")) player.setRace(request.getRace());
        if(changes.contains("profession")) player.setProfession(request.getProfession());
        if(changes.contains("experience")){
            player.setExperience(request.getExperience());
            player.setLevel(сalculateLvl(player.getExperience()));
            player.setUntilNextLevel(calculateUnitForNextLvl(player.getLevel(), player.getExperience()));
        }

    return player;
    }




    private Integer сalculateLvl(Integer experience){
        return (int) (Math.sqrt(2500 + 200 * experience)-50)/100;
    }
    private Integer calculateUnitForNextLvl(int lvl, int experience){
        return 50 * (lvl + 1) * (lvl + 2) - experience;
    }

    private boolean isValidDate(Long date) {
        long dateMin = 946684800000l;
        long dateMax = 32503680000000l;

        if (date.compareTo(dateMin) < 0 || date.compareTo(dateMax) > 0) {
            return false;
        }
        return true;
    }
}
