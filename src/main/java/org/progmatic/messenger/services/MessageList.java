package org.progmatic.messenger.services;

import org.progmatic.messenger.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    private static List<Message> messageList = new ArrayList<>();
    private static int messageCounter;

    public MessageList() {
        messageCounter = 1;
        addMessageToList(new Message("Szia-bia!", "Béla"));
        addMessageToList(new Message("Na, helló!", "Lajos"));
        addMessageToList(new Message("Cső!", "Lajos"));
        addMessageToList(new Message("Halihó!", "Béla"));
        addMessageToList(new Message("Na, mi van?", "Józsi"));
    }

    public static List<Message> getMessageList() {
        return messageList;
    }

    public static void addMessageToList(Message message) {
        message.setMessageId(addIdToMessage());
        messageList.add(message);
    }

    private static int addIdToMessage() {
        return messageCounter++;
    }
}
