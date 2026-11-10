package com.gamerinc.guessthenumber.model;


public class GameSession {

    private int randomNumber;
    private int attempts;
    private boolean gameOver;
    private boolean won;
    private String message;

    public GameSession(int randomNumber) {
        this.randomNumber = randomNumber;
        this.attempts = 0;
        this.gameOver = false;
        this.won = false;
        this.message = "Game started!";
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}