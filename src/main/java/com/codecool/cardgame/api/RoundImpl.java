package com.codecool.cardgame.api;

public class RoundImpl implements Round {

    private Player player1;
    private Player player2;
    private String chosenAttribute;

    public RoundImpl() {
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public String getChosenAttribute() {
        return chosenAttribute;
    }

    @Override
    public void setPlayer1(Player player) {
        player1 = player;
    }

    @Override
    public void setPlayer2(Player player) {
        player2 = player;
    }

    @Override
    public void setChosenAttribute(String attribute) {
        chosenAttribute = attribute;
    }

    @Override
    public Player getWinner() {
        FighterCard player1Card = player1.getChosenCard();
        FighterCard player2Card = player2.getChosenCard();
        switch (chosenAttribute){
            case "Defense":
                if(player1Card.getDefense() > player2Card.getDefense()){
                   decreaseHP(player2,player1Card.getDamage());
                   return player1;
                }
                else if (player1Card.getDefense() < player2Card.getDefense())
                {
                    decreaseHP(player1,player2Card.getDamage());
                    return player2;
                }
                return null;
            case "Damage":
                if(player1Card.getDamage() > player2Card.getDamage()){
                    decreaseHP(player2,player1Card.getDamage());
                    return player1;
                }
                else if (player1Card.getDamage() < player2Card.getDamage())
                {
                    decreaseHP(player1,player2Card.getDamage());
                    return player2;
                }
                return null;
            case "Intelligence":
                if(player1Card.getIntelligence() > player2Card.getIntelligence()){
                    decreaseHP(player2,player1Card.getDamage());
                    return player1;
                }
                else if (player1Card.getIntelligence() < player2Card.getIntelligence())
                {
                    decreaseHP(player1,player2Card.getDamage());
                    return player2;
                }
                return null;
            default:
                return null;
        }
    }

    @Override
    public void decreaseHP(Player player , int damage) {
        player.decreaseHp(damage);
    }

}
