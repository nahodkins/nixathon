package com.team.name.bestrestservice.pojo;

public class GameState {

    private int gameId;
    private char[][] board;
    String player;

    public int getGameId() {
        return gameId;
    }

    public GameState setGameId(int gameId) {
        this.gameId = gameId;
        return this;
    }

    public char[][] getBoard() {
        return board;
    }

    public GameState setBoard(char[][] board) {
        this.board = board;
        return this;
    }

    public String getPlayer() {
        return player;
    }

    public GameState setPlayer(String player) {
        this.player = player;
        return this;
    }
}
