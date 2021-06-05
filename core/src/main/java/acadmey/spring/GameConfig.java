package acadmey.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // == fields ==
    @Value("${game.maxNumber}")
    private int maxNumber;

    private  int guessCount=8;

    @Bean
    @MaxNumber
    public int getMaxNumber()
    {
        return maxNumber;
    }

//    @Bean
//    @GuessCount
//    public int getGuessCount()
//    {
//        return guessCount;
//    }
}
