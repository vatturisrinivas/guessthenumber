package com.gamerinc.guessthenumber.controller;

import org.springframework.web.bind.annotation.*;

import com.gamerinc.guessthenumber.dto.GameResponse;
import com.gamerinc.guessthenumber.service.GameService;

@RestController
@RequestMapping("/api/game")
//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "https://guess-the-number-delta-dun.vercel.app"
	})
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public GameResponse startGame() {
        gameService.startGame();
        return gameService.getResponse();
    }

    @PostMapping("/guess")
    public GameResponse makeGuess(@RequestParam int guess) {
        gameService.makeGuess(guess);
        return gameService.getResponse();
    }
}