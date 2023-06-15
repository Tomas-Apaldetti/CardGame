package com.core.g3.Match.Phase;

import java.util.List;
import java.util.stream.Collectors;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Phase.Exceptions.AcctionNotPossibleException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;

public class MainPhase implements IPhase {

    private final Player current;
    private final Player rival;

    public MainPhase(Player current, Player rival) {
        this.current = current;
        this.rival = rival;
    }

    @Override
    public IPhase summon(ICard card, ActiveZoneType zone) {
        this.current.summonInZone(card, zone);
        return this;
    }

    @Override
    public IPhase useArtefact(CardInGame card, Player player) {

        throw new AcctionNotPossibleException();
    }

    @Override
    public IPhase useAction(ICard card, Player targetPlayer) {
        ActiveZone temporal = new ActiveZone(ActiveZoneType.Temporal, new Amount(Integer.MAX_VALUE), false);
        CardInGame cig = temporal.addCard(card, this.current);
        OriginalAction og = cig.action(this.current, targetPlayer);
        ResolutionStack rstack = new ResolutionStack(og);
        return new ReactionPhase(this.current, this.rival, rstack, this, temporal);
    }

    public IPhase useAction(ICard card, List<CardInGame> targetCards) {
        ActiveZone temporal = new ActiveZone(ActiveZoneType.Temporal, new Amount(Integer.MAX_VALUE), false);
        CardInGame cig = temporal.addCard(card, this.current);

        List<IAttackable> attackables = targetCards.stream().filter(c -> c.isAttackable()).collect(Collectors.toList());

        OriginalAction og = cig.action(attackables, this.current, this.rival);
        ResolutionStack rstack = new ResolutionStack(og);
        return new ReactionPhase(this.current, this.rival, rstack, this, temporal);
    }

    @Override
    public void moveCreature(CardInGame card, ActiveZoneType zoneToMove) {
        throw new AcctionNotPossibleException();
    }

    @Override
    public Player activePlayer() {
        return null;
    }
}
