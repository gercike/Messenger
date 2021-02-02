package org.progmatic.messenger.model;

import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    private String messageText;

    @ManyToOne
    private Message commentTo;

    @OneToMany(mappedBy = "commentTo")
    private List<Message> reactions;

    private LocalDateTime timeOfCreation;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private GercikeUser messageAuthor;

    public Message() {
        this.timeOfCreation = LocalDateTime.now();
    }

    public Message(String text) {
        this.messageText = text;
        this.timeOfCreation = LocalDateTime.now();
    }

    public Message getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(Message commentTo) {
        this.commentTo = commentTo;
    }

    public List<Message> getReactions() {
        return reactions;
    }

    public void setReactions(List<Message> reactions) {
        this.reactions = reactions;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public GercikeUser getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(GercikeUser messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public LocalDateTime getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(LocalDateTime localDateTime) {
        this.timeOfCreation = localDateTime;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
