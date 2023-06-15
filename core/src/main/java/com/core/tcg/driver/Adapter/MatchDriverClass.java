package com.core.tcg.driver.Adapter;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.Deck;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.IMatch;
import com.core.g3.Match.Match;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.GameMode.GameMode1;
import com.core.g3.Match.Phase.PhaseType;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.MatchEndCondition.PlainHP;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.g3.User.User;
import com.core.tcg.driver.*;

import java.util.List;
import java.util.Optional;

public class MatchDriverClass implements MatchDriver<ICard> {
    private IMatch match;

    public MatchDriverClass(IMatch match) {
        this.match = match;
    }

    @Override
    public List<DriverCardName> deckOrder(DriverMatchSide player) {
        return match.getPlayer(DriverMapper.toPlayerZone(player)).retrieveDeckOrder();
    }

    @Override
    public void forceDeckOrder(DriverMatchSide player, List<DriverCardName> cards) {
        match.forceDeckOrder(DriverMapper.toPlayerZone(player), DriverMapper.toDriverCardName(cards));
    }

    @Override
    public void start() {
        this.match.startMatch(PlayerZone.Blue);
    }

    @Override
    public void skipToPhase(DriverMatchSide player, DriverTurnPhase phase) {
        PlayerZone playerZone = DriverMapper.toPlayerZone(player);
        PhaseType turnPhase = DriverMapper.toTurnPhase(phase);
        this.match.skipToPhase(playerZone, turnPhase);
    }

    @Override
    public ICard summon(DriverMatchSide player, DriverCardName card, DriverActiveZone zone) {
        PlayerZone playerZone = DriverMapper.toPlayerZone(player);
        CardName cardName = DriverMapper.toCardName(card);
        ActiveZoneType activeZoneType = DriverMapper.toActiveZoneType(zone);

        return this.match.summon(playerZone, cardName, activeZoneType);
    }

    @Override
    public int getCreatureHitpoints(ICard card) {
        return this.match.getCreatureHitpoints(card);
    }

    @Override
    public void attackCreature(ICard creature, int index, ICard target) {
        this.match.attackCreature(creature,index,target);
    }

    @Override
    public void attackPlayer(ICard creature, int index) {
        this.match.attackPlayer(creature,index);
    }

    @Override
    public void activateArtifact(ICard artifact, int index, Optional<DriverMatchSide> targetPlayer, List<ICard> targets) {
        this.match.activateArtifact(artifact,index,DriverMapper.toOptionalPlayerZone(targetPlayer),targets);
    }

    @Override
    public void activateAction(DriverMatchSide player, DriverCardName card, int index,
            Optional<DriverMatchSide> targetPlayer, List<ICard> targetCards) {
        PlayerZone playerZone = DriverMapper.toPlayerZone(player);
        CardName cardName = DriverMapper.toCardName(card);
        this.match.activateAction(playerZone,cardName,index,DriverMapper.toOptionalPlayerZone(targetPlayer),targetCards);
    }

    @Override
    public void startReactionWindow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startReactionWindow'");
    }

    @Override
    public void endReactionWindow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endReactionWindow'");
    }

    @Override
    public void activateReactionFromHand(DriverMatchSide player, DriverCardName card,
            Optional<DriverMatchSide> targetPlayer, List<ICard> targetCards) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateReactionFromHand'");
    }

    @Override
    public void activateReactionFromActiveZone(ICard card, Optional<DriverMatchSide> targetPlayer,
            List<ICard> targetCards) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateReactionFromActiveZone'");
    }

    @Override
    public int playerHealth(DriverMatchSide player) {
        return this.match.playerHealth(DriverMapper.toPlayerZone(player));
    }

    @Override
    public int playerEnergy(DriverMatchSide player, DriverEnergyType energyType) {
        return this.match.playerEnergy(DriverMapper.toPlayerZone(player),DriverMapper.toEnergyType(energyType)).available();
    }

    @Override
    public Optional<DriverMatchSide> winner() {
        return DriverMapper.toOptionalDriverMatchSide(this.match.getWinner());
    }
}
