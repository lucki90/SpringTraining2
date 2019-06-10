package beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;

@Component(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MessagePrinter {

    @Autowired
    private MessageProducer producer;

    public void printeMessage(){
        System.out.println(producer.getNumber());
    }

}
