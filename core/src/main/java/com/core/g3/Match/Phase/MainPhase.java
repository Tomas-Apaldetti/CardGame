package com.core.g3.Match.Phase;

import java.util.List;
import java.util.stream.Collectors;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Match;
import com.core.g3.Match.Phase.Exceptions.ActionNotPossibleException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;

public class MainPhase implements IPhase {

    private final Player current;
    private final Player rival;
    private final Match match;

    public MainPhase(Player current, Player rival, Match match) {
        this.current = current;
        this.rival = rival;
        this.match = match;
    }

    @Override
    public IPhase summon(ICard card, ActiveZoneType zone) {
        this.current.summonInZone(card, zone);
        return this;
    }

    @Override
    public IPhase useAction(ICard card, Player targetPlayer) {
        ActiveZone temporal = new ActiveZone(ActiveZoneType.Temporal, new Amount(Integer.MAX_VALUE), false);
        CardInGame cig = temporal.addCard(card, this.current);
        OriginalAction og = cig.action(this.current, targetPlayer);
        ResolutionStack rstack = new ResolutionStack(og);
        return new ReactionPhase(this.current, this.rival, rstack, this, this.match, temporal);
    }

    @Override
    public IPhase useAction(ICard card, List<CardInGame> targetCards) {
        ActiveZone temporal = new ActiveZone(ActiveZoneType.Temporal, new Amount(Integer.MAX_VALUE), false);
        CardInGame cig = temporal.addCard(card, this.current);

        List<IAttackable> attackables = this.getAttackables(targetCards);

        OriginalAction og = cig.action(attackables, this.current, this.rival);
        ResolutionStack rstack = new ResolutionStack(og);
        return new ReactionPhase(this.current, this.rival, rstack, this, this.match, temporal);
    }

    @Override
    public IPhase useArtifact(CardInGame card, Player player) {
        OriginalAction og = card.artifact(this.current, player);
        ResolutionStack rStack = new ResolutionStack(og);
        return new ReactionPhase(this.current, this.rival, rStack, this, this.match);
    }

    @Override
    public IPhase useArtifact(CardInGame card, List<CardInGame> targets) {
        List<IAttackable> attackables = this.getAttackables(targets);

        OriginalAction og = card.artifact(attackables, this.current, this.rival);
        ResolutionStack rStack = new ResolutionStack(og);
        return new ReactionPhase(this.current, this.rival, rStack, this, this.match);
    }

    private List<IAttackable> getAttackables(List<CardInGame> from) {
        return from.stream().filter(c -> c.isAttackable()).collect(Collectors.toList());
    }

    @Override
    public void moveCreature(CardInGame card, ActiveZoneType zoneToMove) {
        throw new ActionNotPossibleException();
    }

    @Override
    public Player activePlayer() {
        return this.current;
    }

    @Override
    public IPhase next() {
        return new AttackPhase(this.current, this.rival, this.match);
    }

    @Override
    public boolean coincide(Player desiredCurrentPlayer, PhaseType phase) {
        return this.current.equals(desiredCurrentPlayer) && PhaseType.Main == phase;
    }

    @Override
    public PhaseType getPhaseType() {
        return PhaseType.Main;
    }
}
