package org.progmatic.messenger.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class SearchExpression {
    private String messageText;

    @NotBlank (message = "ide kell valami")
    private String messageAuthor;
    private int messageId;
    private LocalDateTime messageCreation;
    private String sortBy;
    private String sortOrder;

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
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

    public String getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(String messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public LocalDateTime getMessageCreation() {
        return messageCreation;
    }

    public void setMessageCreation(LocalDateTime messageCreation) {
        this.messageCreation = messageCreation;
    }
}

