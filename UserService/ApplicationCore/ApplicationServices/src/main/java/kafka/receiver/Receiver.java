package kafka.receiver;

import exceptions.CannotDeleteItem;
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

    @Value("${spring.kafka.topic.userCreated:USER_TOPIC}")
    private String userTopic;

    @KafkaListener(topics = "${spring.kafka.topic.userResponseQueue:USER_RESPONSE_QUEUE}")
    public void receive(Message message) {
        LOGGER.info("received payload='{}'", message);
        try {
            switch (message.getAction()) {
                case CREATE_SUCCESS, DELETE_SUCCESS, UPDATE_SUCCESS:
                    LOGGER.info("Transaction successful ='{}'", message.getAction());
                    break;
                case FAILED_TO_CREATE:
                    LOGGER.info("Failed to create user ='{}'", message.getUserDto().getUuid());
                    userService.deleteUser(message.getUserDto().getUuid());
                    Message deleteUserMessage = new Message(Action.DELETE, message.getUserDto());
                    LOGGER.info("Sending delete message for user ='{}'", message.getUserDto().getUuid());
                    sender.send(userTopic, deleteUserMessage);

                case FAILED_TO_DELETE:
                    LOGGER.info("Transaction failed ='{}'", message.getAction());
                    break;

                case FAILED_TO_UPDATE:
                    LOGGER.info("Transaction failed ='{}'", message.getAction());
                    break;

                default:
                    return;
            }
        } catch (ItemNotFound e) {
            // ignore
        }
    }
}
