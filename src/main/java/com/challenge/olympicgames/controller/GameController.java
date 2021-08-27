package com.challenge.olympicgames.controller;

import com.challenge.olympicgames.model.GameModel;
import com.challenge.olympicgames.model.dto.GameDto;
import com.challenge.olympicgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "game")
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping
    public List<GameModel> findGames() {
        return service.list();
    }

    @PostMapping
    public GameModel createGame(@RequestBody GameModel gameModel) {
        return service.createGame(gameModel);
    }
}
