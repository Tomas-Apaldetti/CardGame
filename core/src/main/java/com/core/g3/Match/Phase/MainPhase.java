package com.core.g3.Match.Phase;

import java.util.List;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Card;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Card.Type.CardTypeName;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Phase.Exceptions.AcctionNotPossibleException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZoneType;

public class MainPhase implements IPhase {
    @Override
    public ICard summon(ICard card, ActiveZoneType zone, Player player){
        player.summonInZone(card, zone);
        return card;
    }

    @Override
    public IPhase useArtefact(CardInGame card, Player player){
        throw new AcctionNotPossibleException();
    }

    @Override
    public IPhase useAction(ICard card, Player player){
        throw new AcctionNotPossibleException();
    }

    @Override
    public void moveCreature(CardInGame card, ActiveZoneType zoneToMove){
        throw new AcctionNotPossibleException();
    }
}
