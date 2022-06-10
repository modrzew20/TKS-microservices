package kafka.producer;

import kafka.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void send(String topic, Message message) {
        LOGGER.info("sending payload='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }
}
