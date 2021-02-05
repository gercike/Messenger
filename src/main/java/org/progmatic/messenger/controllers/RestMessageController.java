package org.progmatic.messenger.controllers;

import org.progmatic.messenger.model.GercikeUser;
import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.SearchExpression;
import org.progmatic.messenger.model.Topic;
import org.progmatic.messenger.services.MessageList;
import org.progmatic.messenger.services.TopicList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/rest")
public class RestMessageController {
    MessageList messageList;
    TopicList topicList;

    @Autowired
    public RestMessageController(MessageList messageList, TopicList topicList) {
        this.messageList = messageList;
        this.topicList = topicList;
    }

//    @RequestMapping(value = {"/createTopic"}, method = GET)
//    public String newTopicInputForm(@ModelAttribute("newTopicObject") Topic topic, Model model) {
//        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("name", ud.getUsername());
//        return "newTopicForm";
//    }

    @RequestMapping(value = {"/messages"}, method = GET)
    public List<Message> showAllMessages() {
        List<Message> restMessageList = messageList.getMessageListFromDB();
        return restMessageList;
    }

    @RequestMapping(value = {"/topic"}, method = POST)
    public Topic createNewTopic(@RequestBody Topic topic) {
        Topic newTopic = new Topic();
        newTopic.setTopicName(topic.getTopicName());
        topicList.addTopicToMySQL(newTopic);
        return newTopic;
    }

    @RequestMapping(value = {"/csrf"}, method = GET)
    public CsrfToken getCsrfOfSession (CsrfToken token){
        return token;
    }

//    @RequestMapping(value = "/messages/{messageId}", method = GET)
//    public String showMessage(@PathVariable("messageId") int id, Model model, @ModelAttribute("searchExpr") SearchExpression se) {
//        model.addAttribute("messages", messageList.getMessageListFromDB().stream().filter(message -> message.getMessageId() == id).collect(Collectors.toList()));
//        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("name", ud.getUsername());
//        model.addAttribute("topicList", topicList.getTopicListFromDB());
//        model.addAttribute("sortOrderList", se.getSortOrderList());
//        model.addAttribute("sortByList", se.getSortByList());
//        return "showMessages";
//    }
//
//    @RequestMapping(value = "/messages/{messageId}", method = GET)
//    public String modifyMessageText(@PathVariable("messageId") int id, Model model, @ModelAttribute("searchExpr") SearchExpression se) {
//        model.addAttribute("messages", messageList.getMessageListFromDB().stream().filter(message -> message.getMessageId() == id).collect(Collectors.toList()));
//        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("name", ud.getUsername());
//        model.addAttribute("topicList", topicList.getTopicListFromDB());
//        model.addAttribute("sortOrderList", se.getSortOrderList());
//        model.addAttribute("sortByList", se.getSortByList());
//        return "showMessages";
//    }
//
//    @RequestMapping(value = "/createNewMessage", method = GET)
//    public String newMessageTypeIn(@ModelAttribute("newMssg") Message message, Model model) {
//        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        message.setMessageAuthor(ud);
//        model.addAttribute("name", ud.getUsername());
//        model.addAttribute("topicList", topicList.getTopicListFromDB());
//        return "newMessageForm";
//    }
}
