package com.core.g3.Match;

import com.core.g3.Card.CardName;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.Phase.PhaseType;
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

    public int getCreatureHitpoints(ICard card);

    public void attackCreature(ICard creature, int index, ICard target);

    public void attackPlayer(ICard creature, int index);

    public int playerHealth(PlayerZone side);

    public IResource playerEnergy(PlayerZone player, EnergyType energyType);

    public Player getPlayer(PlayerZone side);

    public Optional<PlayerZone> getWinner();

    public void skipToPhase(PlayerZone player, PhaseType phase);

    public ICard summon(PlayerZone side, CardName cardName, ActiveZoneType zone);
}
