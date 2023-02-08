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
        State state = new State(gameState.getBoard(), gameState.getPlayer());
        MiniMaxAgent miniMaxAgent = new MiniMaxAgent(getFirstEmptyRow(gameState.getBoard()));
        int action = miniMaxAgent.getAction(state);
        MyResponseBody myResponseBody = new MyResponseBody();
        myResponseBody.setColumn(action);
        return ResponseEntity.ok(myResponseBody);
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
