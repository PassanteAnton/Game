package com.example.game.controllers;

import com.example.game.exeption.NotValidDateException;
import com.example.game.models.Player;
import com.example.game.repo.PlayersRepo;
import com.example.game.repo.requests.PlayerRequest;
import com.example.game.repo.requests.UpdateRequest;
import com.example.game.repo.specification.PlayerSpecification;
import com.example.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayersRepo playersRepo;

    @PostMapping("/players")
    public ResponseEntity<?> create(@Valid @RequestBody Player player, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        try {
            playerService.create(player);
        }catch (NotValidDateException e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

//    Test body
//    {
//
//        "name": "Tom",
//            "title": "REMBO",
//            "race": "Elf",
//            "profession":"Paladin",
//            "birthday":32503670000000,
//            "experience":88000
//    }



    @GetMapping("/players/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id")long id){
        if(id < 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Player> player = playersRepo.findById(id);

        if(player.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player.get(), HttpStatus.OK);
    }


    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){

        Optional<Player> player = playersRepo.findById(id);

        if(player.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playersRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/players")
    public Page<Player> getByRequest(@ModelAttribute PlayerRequest request,  Model model,
                                     @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) Integer pageNumber,
                                     @RequestParam(required = false) PlayOrder order) {
        if(pageSize == null) pageSize = 3;
        if(pageNumber == null)pageNumber = 0;
        if(order == null) order = PlayOrder.ID;
        Sort sort = Sort.by(order.getFieldName());
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);

        Specification<Player> spec = new PlayerSpecification(request);
        Page<Player> players = playersRepo.findAll(spec, pageable);

        return players;
    }

    @PostMapping("/players/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable(value = "id") long id,
                                          @Valid @RequestBody UpdateRequest request,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Player> player = playersRepo.findById(id);
        if(player.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            try {
                playerService.update(player.get(), request);
            }catch (NotValidDateException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


    return new ResponseEntity<>(playersRepo.save(player.get()), HttpStatus.OK);
    }
}
