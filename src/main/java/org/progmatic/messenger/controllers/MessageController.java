package org.progmatic.messenger.controllers;

import org.progmatic.messenger.model.*;
import org.progmatic.messenger.services.MessageList;
import org.progmatic.messenger.services.TopicList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Scope("singleton")
public class MessageController {
    MessageList messageList;
    TopicList topicList;

    @Autowired
    public MessageController(MessageList messageList, TopicList topicList) {
        this.messageList = messageList;
        this.topicList = topicList;
    }

    @RequestMapping(value = {"/createTopic"}, method = GET)
    public String newTopicInputForm(@ModelAttribute("newTopicObject") Topic topic, Model model) {
        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", ud.getUsername());
        return "newTopicForm";
    }

    @RequestMapping(value = {"/createTopic"}, method = POST)
    public String createNewTopic(@ModelAttribute("newTopicObject") Topic topic, Model model) {
        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", ud.getUsername());
        topicList.addTopicToMySQL(topic);
        return "redirect:/messages";
    }

    @RequestMapping(value = {"/messages"}, method = GET)
    public String showAllMessages(@ModelAttribute("searchExpr") SearchExpression se, Model model) {
        model.addAttribute("messages", messageList.getMessageListFromDB());
        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", ud.getUsername());
        model.addAttribute("topicList", topicList.getTopicListFromDB());
        model.addAttribute("sortOrderList", se.getSortOrderList());
        model.addAttribute("sortByList", se.getSortByList());
        se.setSelectedSortOrder("ascending");
        se.setSelectedSortBy("author");
        return "showMessages";
    }

    @RequestMapping(value = "/messages/{messageId}", method = GET)
    public String showMessage(@PathVariable("messageId") int id, Model model, @ModelAttribute("searchExpr") SearchExpression se) {
        model.addAttribute("messages", messageList.getMessageListFromDB().stream().filter(message -> message.getMessageId() == id).collect(Collectors.toList()));
        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", ud.getUsername());
        model.addAttribute("topicList", topicList.getTopicListFromDB());
        model.addAttribute("sortOrderList", se.getSortOrderList());
        model.addAttribute("sortByList", se.getSortByList());
        return "showMessages";
    }

    @RequestMapping(value = "/createNewMessage", method = GET)
    public String newMessageTypeIn(@ModelAttribute("newMssg") Message message, Model model) {
        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setMessageAuthor(ud);
        model.addAttribute("name", ud.getUsername());
        model.addAttribute("topicList", topicList.getTopicListFromDB());
        return "newMessageForm";
    }

    @RequestMapping(value = "/newMessage", method = POST)
    public String createNewMessage(@ModelAttribute("newMssg") Message message) {
//        if (br.hasErrors()){
//            return "newMessageForm";
//        }
        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setMessageAuthor(ud);
        messageList.addMessageToMySQL(message);
        return "redirect:/messages";
    }

//    @RequestMapping(value = "/reactionTo/{messageId}", method = GET)
//    public String reactionForm(@PathVariable("messageId") int id, Model model) {
//
//
//    }

    @RequestMapping(value = "/searchInMessages", method = POST)
    public String searchInMessages(@ModelAttribute("searchExpr") @Valid SearchExpression se, BindingResult br, Model model) {
        System.out.println("br-ben van valami: " + br.hasErrors());
        model.addAttribute("messages", messageList.getMessageListFromDB(se));
        model.addAttribute("topicList", topicList.getTopicListFromDB());
        model.addAttribute("sortOrderList", se.getSortOrderList());
        model.addAttribute("sortByList", se.getSortByList());
        return "showMessages";
    }

    private List<Message> filterListByAuthor(String author) {
        return messageList.getMessageListFromDB().stream().filter(message -> message.getMessageAuthor().equals(author)).collect(Collectors.toList());
    }

    private List<Message> filterListByTextSubstring(String textSubstring) {
        return messageList.getMessageListFromDB().stream().filter(message -> message.getMessageText().contains(textSubstring)).collect(Collectors.toList());
    }

}
