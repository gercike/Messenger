package org.progmatic.messenger.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearchExpression {
    private String messageText;

    @NotBlank (message = "ide kell valami")
    private String messageAuthor;
    private String messageTopic;
    private int messageId;
    private LocalDateTime messageCreation;
    private String selectedSortOrder;
    private String selectedSortBy;
    private List<String> sortByList;
    private List<String> sortOrderList;

    public SearchExpression() {
        sortByList = new ArrayList<>();
        sortByList.add("messageId");
        sortByList.add("author");
        sortByList.add("datetime of message");
        sortOrderList = new ArrayList<>();
        sortOrderList.add("ascending");
        sortOrderList.add("descending");
    }

    public String getSelectedSortBy() {
        return selectedSortBy;
    }

    public void setSelectedSortBy(String selectedSortBy) {
        this.selectedSortBy = selectedSortBy;
    }

    public String getSelectedSortOrder() {
        return selectedSortOrder;
    }

    public void setSelectedSortOrder(String selectedSortOrder) {
        this.selectedSortOrder = selectedSortOrder;
    }

    public String getMessageTopic() {
        return messageTopic;
    }

    public void setMessageTopic(String messageTopic) {
        this.messageTopic = messageTopic;
    }

    public void setSortByList(List<String> sortByList) {
        this.sortByList = sortByList;
    }

    public void setSortOrderList(List<String> sortOrderList) {
        this.sortOrderList = sortOrderList;
    }

    public List<String> getSortByList() {
        return sortByList;
    }

    public List<String> getSortOrderList() {
        return sortOrderList;
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

