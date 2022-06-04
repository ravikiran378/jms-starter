package com.learning.jms.jmsstarter.producer;

import com.learning.jms.jmsstarter.util.UtilScanner;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.time.Instant;

public class JMSActiveMQProducer {
    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("JMSSTARTER.TESTQ");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            UtilScanner.consoleLineScanner((message) -> {
                try {
                    producer.send(session.createTextMessage(message+" @"+ Instant.now()));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
