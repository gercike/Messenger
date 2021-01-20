package org.progmatic.messenger.controllers;

import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.SearchExpression;
import org.progmatic.messenger.services.MessageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Scope("singleton")
public class MessageController {

    public MessageController() {
        new MessageList();
    }

    @RequestMapping(value = {"/messages"}, method = GET)
    public String showAllMessages(@ModelAttribute("searchExpr") SearchExpression se, Model model) {
        model.addAttribute("messages", MessageList.getMessageList());
        return "showMessages";
    }

    @RequestMapping(value = "/messages/{messageId}", method = GET)
    public String showMessage(@PathVariable("messageId") int id, Model model, @ModelAttribute("searchExpr") SearchExpression se) {
        model.addAttribute("messages", MessageList.getMessageList().stream().filter(message -> message.getMessageId() == id).collect(Collectors.toList()));
        return "showMessages";
    }

    @RequestMapping(value = "/createNewMessage", method = GET)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public String newMessageTypeIn(@ModelAttribute("newMssg") Message message) {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setMessageAuthor(ud.getUsername());
        return "newMessageForm";
    }

    @RequestMapping(value = "/newMessage", method = POST)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public String createNewMessage(@ModelAttribute("newMssg") Message message) {
//        if (br.hasErrors()){
//            return "newMessageForm";
//        }
        MessageList.addMessageToList(message);
        return "redirect:/messages";
    }

    @RequestMapping(value = "/searchInMessages", method = POST)
    public String searchInMessages(@ModelAttribute("searchExpr") @Valid SearchExpression se, BindingResult br, Model model) {
        System.out.println("br-ben van valami: " + br.hasErrors());
        if (br.hasErrors()) {
            return "showMessages";
        }
//        if (se.getMessageAuthor().length() > 0) {
//            model.addAttribute("messages", filterListByAuthor(se.getMessageAuthor()));
//        } else {
//            model.addAttribute("messages", MessageList.getMessageList());
//        }
//        if (se.getMessageText().length() > 0) {
//            model.addAttribute("messages", filterListByTextSubstring(se.getMessageText()));
//        }
        return "showMessages";
    }

    private List<Message> filterListByAuthor(String author) {
        return MessageList.getMessageList().stream().filter(message -> message.getMessageAuthor().equals(author)).collect(Collectors.toList());
    }

    private List<Message> filterListByTextSubstring(String textSubstring) {
        return MessageList.getMessageList().stream().filter(message -> message.getMessageText().contains(textSubstring)).collect(Collectors.toList());
    }

}
