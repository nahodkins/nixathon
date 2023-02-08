package com.team.name.bestrestservice.controller;

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

        MyResponseBody myResponseBody = new MyResponseBody();
        myResponseBody.setColumn(2);
        return ResponseEntity.ok(myResponseBody);
    }
}
