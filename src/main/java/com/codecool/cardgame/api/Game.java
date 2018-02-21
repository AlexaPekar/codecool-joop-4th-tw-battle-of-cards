package com.codecool.cardgame.api;

import com.codecool.cardgame.api.exception.NoManaException;

public interface Game {

    Player getPlayer1();
    Player getPlayer2();
    String getChosenAttribute();
    void setPlayer1(Player player);
    void setPlayer2(Player player);
    void setChosenAttribute(String attribute);
    Player getWinner();
    void decideSpell(SpellCard spellCard) throws NoManaException;
    boolean canUseSpell();
    void decreaseHP(Player player, int damage);

}
