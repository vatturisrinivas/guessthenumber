package com.gamerinc.guessthenumber.service;


import java.util.Random;
import org.springframework.stereotype.Service;

import com.gamerinc.guessthenumber.dto.GameResponse;
import com.gamerinc.guessthenumber.model.GameSession;

@Service
public class GameService {

    private GameSession game;

    private final Random random = new Random();
    
    public GameResponse getResponse() {

        return new GameResponse(
            game.getAttempts(),
            game.isGameOver(),
            game.isWon(),
            game.getMessage(),
            game.isGameOver() ? game.getRandomNumber() : null
        );
    }

    public GameSession startGame() {
        int number = random.nextInt(10) + 1;

        game = new GameSession(number);

        return game;
    }

    public GameSession makeGuess(int guess) {

        if (game == null) {
            throw new IllegalStateException("Start a game first!");
        }

        if (game.isGameOver()) {
            throw new IllegalStateException("Game is already over!");
        }

        if (guess < 1 || guess > 10) {
            throw new IllegalArgumentException("Guess between 1 and 10!");
        }

        int attempts = game.getAttempts() + 1;

        game.setAttempts(attempts);

        if (guess == game.getRandomNumber()) {

            game.setWon(true);
            game.setGameOver(true);
            game.setMessage("Congratulations! You won!");

        } else if (attempts == 3) {

            game.setGameOver(true);
            game.setMessage("Game over! Better luck next time.");

        } else if (guess > game.getRandomNumber()) {

            game.setMessage("Too high!");

        } else {

            game.setMessage("Too low!");
        }

        return game;
    }
}