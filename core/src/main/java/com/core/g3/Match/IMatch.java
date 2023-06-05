package com.core.g3.Match;


import com.core.g3.Card.Card;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Player.Resources.IResource;
import com.core.tcg.driver.DriverCardName;

import java.util.List;

public interface IMatch {
    public void startMatch();

    // @TODO: Change the DriverCardName to a CardType class
    public void forceDeckOrder(Player player, List<DriverCardName> cards);

    // @TODO: Remove zone String type. Create a Zone Class
    public void summon(Player player, DriverCardName card, String zone);

    public int getCreatureHitpoints(Card card);

    public void attackCreature(Card creature, int index, Card target);

    public void attackPlayer(Card creature, int index);

    public int playerHealth(Player player);

    public IResource playerEnergy(Player player, EnergyType energyType);
}
