package com.core.g3.Match.Phase;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Phase.Exceptions.ActionNotPossibleException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.List;

public interface IPhase {

    default IPhase summon(ICard card, ActiveZoneType zone) {
        throw new ActionNotPossibleException();
    }

    default IPhase useArtifact(CardInGame card, Player player) {
        throw new ActionNotPossibleException();
    }

    default IPhase useArtifact(CardInGame card, List<CardInGame> targets){
        throw new ActionNotPossibleException();
    }

    default IPhase useAction(ICard card, Player targetPlayer) {
        throw new ActionNotPossibleException();
    }

    default IPhase useAction(ICard card, List<CardInGame> targetCards) {
        throw new ActionNotPossibleException();
    }

    default void moveCreature(CardInGame card, ActiveZoneType zoneToMove) {
        throw new ActionNotPossibleException();
    }

    default IPhase attack(CardInGame card, Amount index, IAttackable attackable) {
        throw new ActionNotPossibleException();
    }

    default IPhase useReaction(CardInGame card, List<CardInGame> targets) {
        throw new ActionNotPossibleException();
    }

    default IPhase useReaction(ICard card, List<CardInGame> targets){
        throw new ActionNotPossibleException();
    }

    default IPhase useReaction(CardInGame card, Player target){
        throw new ActionNotPossibleException();
    }

    default IPhase useReaction(ICard card, Player target) {
        throw new ActionNotPossibleException();
    }

    default IPhase skipReaction(){
        return this;
    }

    Player activePlayer();

    IPhase next();

    default void initialEffects(){
    }
    boolean coincide(Player desiredCurrentPlayer, PhaseType phase);
}