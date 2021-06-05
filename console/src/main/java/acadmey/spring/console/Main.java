package acadmey.spring.console;

import acadmey.spring.AppConfig;
import acadmey.spring.MessageGenerator;
import acadmey.spring.NumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {



    //private static final String CONFIG_LOCATION="beans.xml";

    public static void main(String[] args) {

        /*ConfigurableApplicationContext context=
                new ClassPathXmlApplicationContext(CONFIG_LOCATION);*/

        ConfigurableApplicationContext context=
                new AnnotationConfigApplicationContext(AppConfig.class);

        NumberGenerator numberGenerator
                =context.getBean(NumberGenerator.class);
        int number=numberGenerator.next();

        log.info("number {}",number);

        // get message generator bean from context (container)
        MessageGenerator messageGenerator=
                context.getBean(MessageGenerator.class);
//        log.info("getMainMessage() "+messageGenerator.getMainMessage());
//        log.info("getResultMessage() "+messageGenerator.getResultMessage());

//    ConsoleNumberGuess consoleNumberGuess
//            =context.getBean(ConsoleNumberGuess.class);

        //game.reset();

        context.close();

    }
}
