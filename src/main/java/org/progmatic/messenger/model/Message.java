package org.progmatic.messenger.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private int messageId;
    private String messageText;
    private String messageAuthor;
    private String timeOfCreation;

    public Message(String text, String author) {
        this.messageText = text;
        this.messageAuthor = author;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATETIMEPATTERN);
        this.timeOfCreation = now.format(formatter);
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageAuthor(String messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public void setTimeOfCreation(String timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageAuthor() {
        return messageAuthor;
    }

    public String getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getMessageId() {
        return messageId;
    }
}
