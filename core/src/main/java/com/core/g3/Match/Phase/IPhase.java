package com.core.g3.Match.Phase;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Phase.Exceptions.AcctionNotPossibleException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.List;

public interface IPhase {

    default IPhase summon(ICard card, ActiveZoneType zone) {
        throw new AcctionNotPossibleException();
    }

    default IPhase useArtifact(CardInGame card, Player player) {
        throw new AcctionNotPossibleException();
    }

    default IPhase useArtifact(CardInGame card, List<CardInGame> targets){
        throw new AcctionNotPossibleException();
    }

    default IPhase useAction(ICard card, Player targetPlayer) {
        throw new AcctionNotPossibleException();
    }

    default IPhase useAction(ICard card, List<CardInGame> targetCards) {
        throw new AcctionNotPossibleException();
    }

    default void moveCreature(CardInGame card, ActiveZoneType zoneToMove) {
        throw new AcctionNotPossibleException();
    }

    default void attack(CardInGame card, Amount index, IAttackable attackable) {
        throw new AcctionNotPossibleException();
    }

    default IPhase useReaction(CardInGame card, List<CardInGame> targets) {
        throw new AcctionNotPossibleException();
    }

    default IPhase useReaction(ICard card, List<CardInGame> targets){
        throw new AcctionNotPossibleException();
    }

    default IPhase useReaction(CardInGame card, Player target){
        throw new AcctionNotPossibleException();
    }

    default IPhase useReaction(ICard card, Player target) {
        throw new AcctionNotPossibleException();
    }

    default void applyLingeringEffects(List<ILingeringEffect> effectsToApply, Player activePlayer) {
        return;
    }

    Player activePlayer();
}