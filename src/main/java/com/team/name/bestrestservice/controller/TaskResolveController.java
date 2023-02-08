package com.team.name.bestrestservice.controller;

import com.team.name.bestrestservice.algo.MiniMaxAgent;
import com.team.name.bestrestservice.algo.State;
import com.team.name.bestrestservice.pojo.GameState;
import com.team.name.bestrestservice.pojo.MyResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskResolveController {

    @GetMapping("/healthz")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/move")
    public ResponseEntity<MyResponseBody> move(@RequestBody GameState gameState) {
        char opponent = getOpponent(gameState);
        int vertical = checkVertical(opponent, gameState.getBoard());
        int horisontal = checkHorizontal(opponent, gameState.getBoard());

        if (vertical != -1) {
            MyResponseBody responseBody = new MyResponseBody();
            responseBody.setColumn(vertical);
            return ResponseEntity.ok(responseBody);
        }

        if (horisontal != -1) {
            MyResponseBody responseBody = new MyResponseBody();
            responseBody.setColumn(horisontal);
            return ResponseEntity.ok(responseBody);
        }

        State state = new State(gameState.getBoard(), gameState.getPlayer());
        MiniMaxAgent miniMaxAgent = new MiniMaxAgent(getFirstEmptyRow(gameState.getBoard()));
        int action = miniMaxAgent.getAction(state);
        MyResponseBody myResponseBody = new MyResponseBody();
        myResponseBody.setColumn(action);
        return ResponseEntity.ok(myResponseBody);
    }

    private char getOpponent(GameState gameState) {
        return gameState.getPlayer().charAt(0) == 'S'
                ? 'F'
                : 'S';
    }

    private int checkHorizontal(char opponent, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            int count = 0;
            var row = board[i];
            for (int j = 0; j < row.length; j++) {
                if (count == 3 && row[j] == '_') {
                    return j;
                }
                if (row[j] == opponent) {
                    count++;
                } else {
                    count = 0;
                }
            }
        }
        return -1;
    }
//        ["_","_","_","_","_","_","_","_","_"],
//        ["S","_","_","_","X","F","_","_","_"],
//        ["F","_","_","_","X","S","S","_","_"],
//        ["F","_","_","_","X","S","F","S","_"],
//        ["S","_","F","_","X","F","S","S","S"],
//        ["F","_","F","_","X","F","S","F","S"]
    private int checkVertical(char opponent, char[][] board) {
        for (int i = board[0].length - 1; i >= 0; i--) {
            int count = 0;
            for (int j = board.length - 1; j >= 0; j--) {
                if (i == 0) {
                    return -1;
                }
                if (count == 3 && board[j][i - 1] == '_') {
                    return i;
                }
                if (board[j][i] == opponent) {
                    count++;
                } else {
                    count = 0;
                }
            }
        }

        return -1;
    }

    private int getFirstEmptyRow(char[][] board) {
        for(int i = board.length-1; i >= 0; i--) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == '_') {
                    return i;
                }
            }
        }
        return 0;
    }
}
