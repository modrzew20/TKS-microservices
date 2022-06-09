package kafka.receiver;

import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import kafka.message.Action;
import kafka.message.Message;
import kafka.producer.Sender;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import service.UserService;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    @Autowired
    UserService userService;
    @Autowired
    private Sender sender;

    @Value("${spring.kafka.topic.userResponseQueue:USER_RESPONSE_QUEUE}")
    private String userResponseQueue;

    // ${spring.kafka.topic.userCreated:
    @KafkaListener(topics = "USER_TOPIC")
    public void receive(Message message) {
        Message responseMessage = null;
        LOGGER.info("received payload='{}'", message);
        try {
            switch (message.getAction()) {
                case CREATE -> {
                    User user = new User(message.getUserDto().getUuid(), message.getUserDto().getLogin());
                    userService.addUserFromMessage(user);
                }
                case DELETE -> userService.deleteLocalUser(message.getUserDto().getLogin());
                case UPDATE -> userService.updateUser(message.getUserDto().getUuid(), message.getUserDto().getLogin());
                default -> {
                    return;
                }
            }
        } catch (ItemNotFound | LoginInUseException e) {
            responseMessage = new Message(Action.getFailureAction(message.getAction()), message.getUserDto());
        }
        if (responseMessage == null) {
            responseMessage = new Message(Action.getSuccessAction(message.getAction()), message.getUserDto());
        }

        sender.send(userResponseQueue, responseMessage);
    }
}
