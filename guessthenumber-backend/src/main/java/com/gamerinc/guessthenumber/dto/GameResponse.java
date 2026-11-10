package com.gamerinc.guessthenumber.dto;

public record GameResponse(
        int attempts,
        boolean gameOver,
        boolean won,
        String message,
        Integer correctNumber
) {}