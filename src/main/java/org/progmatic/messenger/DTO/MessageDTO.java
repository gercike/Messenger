package org.progmatic.messenger.DTO;

import org.progmatic.messenger.model.GercikeUser;
import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.Topic;

import java.time.LocalDateTime;
import java.util.List;

public class MessageDTO {

    private int messageId;

    private String messageText;

    private LocalDateTime timeOfCreation;

    private String topic;

    private String messageAuthor;

    public MessageDTO() {
        this.timeOfCreation = LocalDateTime.now();
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(LocalDateTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(String messageAuthor) {
        this.messageAuthor = messageAuthor;
    }
}
