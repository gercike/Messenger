package org.progmatic.messenger.services;

import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicList {
    List<Topic> topicList = new ArrayList<>();

    @Autowired
    TopicList self;

    @PersistenceContext
    EntityManager em;

    public TopicList() {

    }

    public List<Topic> getTopicListFromDB() {
        List<Topic> topicListFromDB = em.createQuery("SELECT topic FROM Topic topic").
                getResultList();
        return topicListFromDB;
    }

    @Transactional
    public void addTopicToList(Topic topic) {
        topicList.add(topic);
        self.addTopicToMySQL(topic);
    }

    @Transactional
    public void addTopicToMySQL(Topic topic) {
        em.persist(topic);
    }
}
