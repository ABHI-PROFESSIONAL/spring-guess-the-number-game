package acadmey.spring;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MessageGeneratorImpl implements MessageGenerator{



    @Autowired
    private  Game game;

    @PostConstruct
    public void postConstruct()
    {
        log.debug("game {}",game);
    }

    @Override
    public String getMainMessage() {

        return "Number is between "+
                game.getSmallest()+
                " and "+
                game.getBiggest()+
                ".  Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon())
            return "You guessed it! The number was "+game.getNumber();
        else if(game.isGameLost())
            return "You lost. The number was "+game.getNumber();
        else if(!game.isValidNNumberRange())
            return "Invalid number range";
        else if(game.getRemainingGuesses()==game.getGuessCount())
            return "What is your first guess ?";
        else {
            String direction="Lower";

            if(game.getGuess() <game.getRemainingGuesses())
                direction="Higher";

            return direction + "! You have "+game.getRemainingGuesses()+ " guesses left";
        }

    }
}
