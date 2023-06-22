package com.core.apirest.model;

import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.Resources.EnergyType;

public class PlayerMatchInformation {
    public String player;
    public int hitPoints;
    public int fireEnergy;
    public int waterEnergy;
    public int plantEnergy;
    public int cardsInHand;
    public int cardsInDeck;

    public static PlayerMatchInformation fromPlayer(Player player) {
        PlayerMatchInformation playerInformation = new PlayerMatchInformation();
        playerInformation.player = player.getUsername();
        playerInformation.fireEnergy = player.getEnergy(EnergyType.Fire).available();
        playerInformation.waterEnergy = player.getEnergy(EnergyType.Water).available();
        playerInformation.plantEnergy = player.getEnergy(EnergyType.Plant).available();
        playerInformation.hitPoints = player.matchEndConditionPoints();
        playerInformation.cardsInHand = player.seeHand().size();
        playerInformation.cardsInDeck = player.getDeck().getCards().size();
        return playerInformation;
    }
}
