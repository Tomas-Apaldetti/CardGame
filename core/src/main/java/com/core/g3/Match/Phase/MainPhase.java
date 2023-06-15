package com.core.g3.Match.Phase;

import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Phase.Exceptions.AcctionNotPossibleException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZoneType;

public class MainPhase implements IPhase {
    private final Player current;
    private final Player rival;

    public MainPhase(Player current, Player rival){
        this.current = current;
        this.rival = rival;
    }
    @Override
    public IPhase summon(ICard card, ActiveZoneType zone){
        this.current.summonInZone(card, zone);
        return this;
    }

    @Override
    public IPhase useArtefact(CardInGame card, Player player){

        throw new AcctionNotPossibleException();
    }

    // @Override
    // public IPhase useAction(ICard card, Player player, int index, Player
    // targetPlayer, List<ICard> targetCards){
    // ActiveZone az = new ActiveZone(ActiveZoneType.Temporal,new
    // Amount(Integer.MAX_VALUE));
    // player.summonInZone(card,az);
    // CardInGame cig = az.getCardInGame(card);
    // }

    @Override
    public void moveCreature(CardInGame card, ActiveZoneType zoneToMove) {
        throw new AcctionNotPossibleException();
    }

    @Override
    public Player activePlayer() {
        return null;
    }
}
