package acadmey.spring;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
@Getter
public class GameImpl implements Game{


    @Autowired
    @Getter(AccessLevel.NONE)
    private NumberGenerator numberGenerator;


    private  int guessCount=8;

    private int number;

    private int smallest;

    private int biggest;

    private int remainingGuesses;

    private boolean validNumberRange=true;

    @Setter
    private int guess;


    // == Constructor based injection
    /*public GameImpl(NumberGenerator numberGenerator)
    {
        this.numberGenerator=numberGenerator;
    }*/

    //== Setter based injection ==
    /*public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }*/




    @PostConstruct
    @Override
    public void reset() {

        smallest=0;
        biggest=numberGenerator.getMaxNumber();
        guess=0;
        remainingGuesses=guessCount;
        number=numberGenerator.next();
        log.debug("the number is{} ",number);

    }

    @PreDestroy
    public  void preDestroy()
    {
        log.debug("in game preDestroy");
    }

    /*@Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess=guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }*/


    @Override
    public void check() {
        checkValidNumberRange();

        if(validNumberRange)
        {
            if(guess>number)
            {
                biggest=guess-1;
            }
        }
        if(guess<number)
        {
            smallest=guess+1;
        }
        remainingGuesses--;
    }

    @Override
    public boolean isValidNNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess==number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses<=0;
    }

    private  void checkValidNumberRange()
    {
        validNumberRange=(guess>=smallest) && (guess<=biggest);
    }
}
