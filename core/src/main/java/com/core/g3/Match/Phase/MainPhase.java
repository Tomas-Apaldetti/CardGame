package com.core.g3.Match.Phase;

import java.util.List;

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
        /**
         * Crear una zona temporal, cap infinita
         * agregar la carta a la zona temporal
         * llamar a la carta in OriginalAction = game.action para solo player
         * Crear una fase de reaccion
         * -- StackResolution
         * -- Optional<La zona temporal>
         * -- De quien es el turno
         * -- Rival
         * -- Quien es el que actua
         */
        // ActiveZone temporal = new ActiveZone(ActiveZoneType.Temporal, new
        // Amount(Integer.MAX_VALUE), false);
        // CardInGame cig = temporal.addCard(card, this.current);
        // OriginalAction og = cig.action(cig, this.current, targetPlayer);
        // ResolutionStack rstack = new ResolutionStack(og);
        // return new ReactionPhase(this.current, targetPlayer, rstack, this);

        throw new AcctionNotPossibleException();
    }

    public IPhase useAction(ICard card, List<ICard> targetCards) {
        throw new AcctionNotPossibleException();
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
