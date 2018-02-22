package com.codecool.cardgame.api;

import com.codecool.cardgame.api.exception.NoManaException;
import com.codecool.cardgame.api.exception.RoundDrawException;
import com.codecool.cardgame.api.exception.WrongInputException;

import java.util.List;

public interface Game {

    Player getCurrentPlayer();
    void setCurrentPlayer(Player player);
    Player getPlayer1();
    Player getPlayer2();
    String getChosenAttribute();
    void setPlayer1(Player player);
    void setPlayer2(Player player);
    void setChosenAttribute(String attribute);
    Player getWinner() throws RoundDrawException, WrongInputException;
    void decideSpell(SpellCard spellCard) throws NoManaException;
    boolean canUseSpell();
    void decreaseHP(Player player, int damage);
    List<Card> getGraveyard();


}
