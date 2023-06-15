package com.core.g3.Match.Phase;

import com.core.g3.Card.Attack.IAttackable;

import java.util.List;

import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Phase.Exceptions.AcctionNotPossibleException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;
import com.core.g3.Match.Zone.ActiveZoneType;

public interface IPhase {

    default ICard summon(ICard card, ActiveZoneType zone, Player player) {
        throw new AcctionNotPossibleException();
    }

    default IPhase useArtefact(CardInGame card, Player player) {
        throw new AcctionNotPossibleException();
    }

    default IPhase useAction(ICard card, Player player) {
        throw new AcctionNotPossibleException();
    }

    default void moveCreature(CardInGame card, ActiveZoneType zoneToMove) {
        throw new AcctionNotPossibleException();
    }

    default void attack(CardInGame card, IAttackable attackable) {
        throw new AcctionNotPossibleException();
    }

    default IPhase useReaction(CardInGame card) {
        throw new AcctionNotPossibleException();
    }

    default IPhase useReaction(ICard card) {
        throw new AcctionNotPossibleException();
    }

    default void applyLingeringEffects(List<ILingeringEffect> effectsToApply, Player activePlayer) {
        return;
    }
}