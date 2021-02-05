package org.progmatic.messenger.services;

import com.mysql.cj.util.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.progmatic.messenger.model.GercikeUser;
import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.QMessage;
import org.progmatic.messenger.model.SearchExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageList {
    @Autowired
    MessageList self;

    @PersistenceContext
    EntityManager em;

    public MessageList() {
    }

    @Transactional
    public List<Message> getMessageListFromDB() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        List<Message> messageListFromDB2 = jpaQueryFactory.selectFrom(QMessage.message).fetch();
        return messageListFromDB2;
    }

    @Transactional
    public List<Message> getMessageListFromDB(SearchExpression se) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!(se.getMessageAuthor().isBlank())) {
            booleanBuilder.and(QMessage.message.messageAuthor.userName.eq(se.getMessageAuthor()));
        }
        if (!(se.getMessageText().isBlank())) {
            booleanBuilder.and(QMessage.message.messageText.contains(se.getMessageText()));
        }
        if (!(se.getMessageTopic().isEmpty())) {
            booleanBuilder.and(QMessage.message.topic.topicName.eq(se.getMessageTopic()));
        }

        return jpaQueryFactory.selectFrom(QMessage.message)
                .where(booleanBuilder)
//                .orderBy(se.getSortOrderList().ge)
                .fetch();
    }

    @Transactional
    public List<Message> getMessageListOfUserFromDB(String userName) {
        List<Message> messageListFromDB = em.createQuery("select m from Message m where m.messageAuthor.userName = :userName")
                .setParameter("userName", userName)
                .getResultList();
        return messageListFromDB;
    }

    public List<Message> getMessageListOfTopic(String topicName) {
        List<Message> messageListFromDB = em.createQuery("select m from Message m where m.topic.topicName = :topicName")
                .setParameter("topicName", topicName)
                .getResultList();
        return messageListFromDB;
    }

    @Transactional
    public void addMessageToMySQL(Message message) {
        em.persist(message);
    }
}
