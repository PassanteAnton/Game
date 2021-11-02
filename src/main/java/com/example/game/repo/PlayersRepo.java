package com.example.game.repo;

import com.example.game.models.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface PlayersRepo extends CrudRepository<Player, Long>, JpaSpecificationExecutor<Player> {

    public Page<Player> findAll(Specification<Player> spec,Pageable pageable);


}
