package com.learning.jms.jmsstarter.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.time.Instant;

public class JMSActiveMQConsumer {
    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("JMSSTARTER.TESTQ");
            MessageConsumer consumer = session.createConsumer(destination);
            for(int i=0; i<1000; i++) {
                Thread.sleep(2*1000);
                Message message = consumer.receive();
                System.out.println(((TextMessage) message).getText());
            }
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
