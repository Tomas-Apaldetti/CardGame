package com.core.g3.Match.Phase;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Card;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Phase.Exceptions.AcctionNotPossibleException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.List;

public interface IPhase {

    default ICard summon(ICard card, ActiveZoneType zone, Player player){
        throw new AcctionNotPossibleException();
    }

    default IPhase useArtefact(CardInGame card, Player player){
        throw new AcctionNotPossibleException();
    }

    default IPhase useAction(ICard card, Player player, int index, Player targetPlayer, List<ICard> targetCards){
        throw new AcctionNotPossibleException();
    }

    default void moveCreature(CardInGame card, ActiveZoneType zoneToMove){
        throw new AcctionNotPossibleException();
    }

    default void attack(CardInGame card, Amount index, IAttackable attackable){
        throw new AcctionNotPossibleException();
    }

    default IPhase useReaction(CardInGame card){
        throw new AcctionNotPossibleException();
    }

    default IPhase useReaction(ICard card){
        throw new AcctionNotPossibleException();
    }

    default void applyLingeringEffects(){
        throw new AcctionNotPossibleException();
    }
}