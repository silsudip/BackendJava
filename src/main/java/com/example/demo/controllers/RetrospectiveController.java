package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.FeedbackItem;
import com.example.demo.models.Retrospective;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@RestController
@RequestMapping("/retrospectives")
public class RetrospectiveController {

    private Map<String, Retrospective> retrospectives = new HashMap<>();

    @PostMapping
    public ResponseEntity<String> createRetrospective(@RequestBody Retrospective retrospective) {
        if (retrospective.getDate() == null || retrospective.getParticipants().isEmpty()) {
            return ResponseEntity.badRequest().body("Date and participants are required fields");
        }
        String name = retrospective.getName();
        if (retrospectives.containsKey(name)) {
            return ResponseEntity.badRequest().body("Retrospective with name '" + name + "' already exists");
        }
        retrospectives.put(name, retrospective);
        return ResponseEntity.status(HttpStatus.CREATED).body("Retrospective created successfully");
    }

    @PostMapping("/{name}/feedback")
    public ResponseEntity<String> addFeedbackItem(@PathVariable String name, @RequestBody FeedbackItem feedbackItem) {
        if (!retrospectives.containsKey(name)) {
            return ResponseEntity.notFound().build();
        }
        retrospectives.get(name).addFeedbackItem(feedbackItem);
        return ResponseEntity.ok("Feedback item added successfully");
    }

    @PutMapping("/{name}/feedback/{index}")
    public ResponseEntity<String> updateFeedbackItem(@PathVariable String name, @PathVariable int index,
            @RequestBody FeedbackItem feedbackItem) {
        if (!retrospectives.containsKey(name) || index < 0
                || index >= retrospectives.get(name).getFeedbackItems().size()) {
            return ResponseEntity.notFound().build();
        }
        retrospectives.get(name).getFeedbackItems().set(index, feedbackItem);
        return ResponseEntity.ok("Feedback item updated successfully");
    }

    @GetMapping
    public Collection<Retrospective> searchRetrospectives() {
        return retrospectives.values();
    }
}
