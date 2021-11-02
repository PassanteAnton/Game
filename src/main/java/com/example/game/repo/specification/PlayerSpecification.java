package com.example.game.repo.specification;

import com.example.game.models.Player;
import com.example.game.models.Race;
import com.example.game.repo.requests.PlayerRequest;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



//https://overcoder.net/q/184498/%D1%84%D0%B8%D0%BB%D1%8C%D1%82%D1%80%D0%B0%D1%86%D0%B8%D1%8F-%D1%81%D1%82%D1%80%D0%BE%D0%BA-%D0%B1%D0%B0%D0%B7%D1%8B-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D1%85-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-spring-data-jpa-%D0%B8-spring-mvc

// ПОДРОБНОСТИ ПО СЫЛКИ
public class PlayerSpecification implements Specification<Player> {
    private PlayerRequest  request;

    public PlayerSpecification(PlayerRequest request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<Predicate>();

        if(request.getName()!=null && !request.getName().isEmpty()){
            predicates.add(cb.equal(root.get("name"),request.getName()));
        }
        if(request.getTitle()!=null && !request.getTitle().isEmpty()){
            predicates.add(cb.equal(root.get("title"),request.getTitle()));
        }
        if(request.getRace()!=null){
            predicates.add((cb.equal(root.get("race"),request.getRace())));
        }
        if(request.getProfession()!=null){
            predicates.add((cb.equal(root.get("profession"),request.getProfession())));
        }

            predicates.add(cb.between(root.get("level"), request.getMinLvl(), request.getMaxLvl()));
            predicates.add(cb.between(root.get("experience"), request.getMinExperience(), request.getMaxExperience()));
            predicates.add(cb.between(root.<Date>get("birthday"), new Date(request.getAfter()),new Date(request.getBefore())));



        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}

