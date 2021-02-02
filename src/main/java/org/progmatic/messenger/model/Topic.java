package org.progmatic.messenger.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topic {

    @Id
    private String topicName;

    @OneToMany(mappedBy = "topic")
    private List<Message> messageList;

    public Topic() {
    }

    public Topic(String name) {
        this.topicName = name;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String name) {
        this.topicName = name;
    }
}
