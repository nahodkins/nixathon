package com.team.name.bestrestservice.controller;

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
    public ResponseEntity<String> move(@RequestBody String request) {
        System.out.println(request);
        return ResponseEntity.ok("OK");
    }
}
