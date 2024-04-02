package com.example.demo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Retrospective {
    private String name;
    private String summary;
    private Date date;
    private List<String> participants;
    private List<FeedbackItem> feedbackItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<FeedbackItem> getFeedbackItems() {
        return feedbackItems;
    }

    public void setFeedbackItems(List<FeedbackItem> feedbackItems) {
        this.feedbackItems = feedbackItems;
    }

     public void addFeedbackItem(FeedbackItem feedbackItem) {
        if (feedbackItems == null) {
            feedbackItems = new ArrayList<>();
        }
        feedbackItems.add(feedbackItem);
    }
}
