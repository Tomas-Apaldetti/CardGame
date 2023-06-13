package com.core.g3.Match;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Player.Resources.IResource;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.List;
import java.util.Optional;

public interface IMatch {
    public void startMatch(PlayerZone firstTurn);

    public void forceDeckOrder(PlayerZone player, List<CardName> cards);

    public void summon(PlayerZone player, ICard card, ActiveZoneType zone);

    public int getCreatureHitpoints(Card card);

    public void attackCreature(Card creature, int index, Card target);

    public void attackPlayer(Card creature, int index);

    public int playerHealth(PlayerZone side);

    public IResource playerEnergy(PlayerZone player, EnergyType energyType);

    public Player getPlayer(PlayerZone side);

    public Optional<PlayerZone> getWinner();
}
