package com.core.g3.Match;

import com.core.g3.Card.Card;
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
    void startMatch(PlayerZone firstTurn);

    void forceDeckOrder(PlayerZone player, List<CardName> cards);

    int getCreatureHitpoints(ICard card);

    void attackCreature(ICard creature, int index, ICard target);

    void attackPlayer(ICard creature, int index);

    int playerHealth(PlayerZone side);

    IResource playerEnergy(PlayerZone player, EnergyType energyType);

    Player getPlayer(PlayerZone side);

    Optional<PlayerZone> getWinner();

    void skipToPhase(PlayerZone player, PhaseType phase);

    ICard summon(PlayerZone side, CardName cardName, ActiveZoneType zone);

    void activateAction(PlayerZone playerZone, CardName cardName, int index, Optional<PlayerZone> toOptionalPlayerZone, List<ICard> targetCards);

    void activateArtifact(ICard artifact, int index, Optional<PlayerZone> toOptionalPlayerZone, List<ICard> targets);
}
