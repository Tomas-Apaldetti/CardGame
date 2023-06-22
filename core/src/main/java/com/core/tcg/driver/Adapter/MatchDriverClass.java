package com.core.tcg.driver.Adapter;

import com.core.g3.Card.CardName;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.Match;
import com.core.g3.Match.Phase.PhaseType;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.tcg.driver.*;

import java.util.List;
import java.util.Optional;

public class MatchDriverClass implements MatchDriver<ICard> {
    private Match match;

    private boolean shouldSkipReactions = true;

    public MatchDriverClass(Match match) {
        this.match = match;
    }

    private void skipReactions(){
        if(this.shouldSkipReactions){
            this.match.skipReaction();
            this.match.skipReaction();
        }
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
    public ICard summon(DriverMatchSide player, DriverCardName card,
            DriverActiveZone zone) {
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
        this.match.attackCreature(creature, index, target);
        this.skipReactions();
    }

    @Override
    public void attackPlayer(ICard creature, int index) {
        this.match.attackPlayer(creature, index);
        this.skipReactions();
    }

    @Override
    public void activateArtifact(ICard artifact, int index, Optional<DriverMatchSide> targetPlayer,
            List<ICard> targets) {
        this.match.activateArtifact(artifact, index, DriverMapper.toOptionalPlayerZone(targetPlayer), targets);
        this.skipReactions();
    }

    @Override
    public void activateAction(DriverMatchSide player, DriverCardName card, int index,
            Optional<DriverMatchSide> targetPlayer, List<ICard> targetCards) {
        PlayerZone playerZone = DriverMapper.toPlayerZone(player);
        CardName cardName = DriverMapper.toCardName(card);
        this.match.activateAction(playerZone, cardName, index, DriverMapper.toOptionalPlayerZone(targetPlayer),
                targetCards);
        this.skipReactions();
    }

    @Override
    public void startReactionWindow() {
        this.shouldSkipReactions = false;
    }

    @Override
    public void endReactionWindow() {
        this.match.skipReaction();
        this.match.skipReaction();
        this.shouldSkipReactions = true;
    }

    @Override
    public void activateReactionFromHand(DriverMatchSide player, DriverCardName card,
            Optional<DriverMatchSide> targetPlayer, List<ICard> targetCards) {
        PlayerZone playerZone = DriverMapper.toPlayerZone(player);
        CardName cardName = DriverMapper.toCardName(card);
        this.match.activateReactionFromHand(playerZone, cardName, DriverMapper.toOptionalPlayerZone(targetPlayer), targetCards);
    }

    @Override
    public void activateReactionFromActiveZone(ICard card, Optional<DriverMatchSide> targetPlayer,
            List<ICard> targetCards) {
        this.match.activateReactionFromZone(card, DriverMapper.toOptionalPlayerZone(targetPlayer), targetCards);
    }

    @Override
    public int playerHealth(DriverMatchSide player) {
        return this.match.playerHealth(DriverMapper.toPlayerZone(player));
    }

    @Override
    public int playerEnergy(DriverMatchSide player, DriverEnergyType energyType) {
        return this.match.playerEnergy(DriverMapper.toPlayerZone(player), DriverMapper.toEnergyType(energyType))
                .available();
    }

    @Override
    public Optional<DriverMatchSide> winner() {

        return DriverMapper.toOptionalDriverMatchSide(this.match.getWinner());
    }
}
