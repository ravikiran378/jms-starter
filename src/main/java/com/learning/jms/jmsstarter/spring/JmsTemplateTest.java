package com.learning.jms.jmsstarter.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsTemplateTest {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("JmsTemplateTest-Context.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) applicationContext.getBean("jmsTemplate");

        jmsTemplate.send("SpringTestJMSQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage text = session.createTextMessage();
                text.setText("Hello!!");
                return text;
            }
        });
        System.exit(0);
    }
}
