package com.core.g3.Match.Phase;

import java.util.List;
import java.util.Optional;

import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Match;
import com.core.g3.Match.Phase.Exceptions.AcctionNotPossibleException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;

public class ReactionPhase implements IPhase {

    private Player current;
    private Player rival;
    private ResolutionStack rstak;
    private MainPhase previous;
    private ActiveZone zone;
    private int skipAmount;
    private Player activePlayer;

    public ReactionPhase(Player current, Player rival, ResolutionStack rstack, MainPhase previous, Match match,
            ActiveZone zone) {
        this.current = current;
        this.rival = rival;
        this.activePlayer = rival;
        this.rstak = rstack;
        this.previous = previous;
        this.skipAmount = 0;
        this.zone = zone;
    }

    public ReactionPhase(Player current, Player rival, ResolutionStack rStack, MainPhase previous, Match match) {
        this(
                current,
                rival,
                rStack,
                previous,
                match,
                new ActiveZone(ActiveZoneType.Temporal, new Amount(Integer.MAX_VALUE), false)
        );
    }

    private Player getContrary(){
        return this.activePlayer.equals(this.current) ? this.rival : this.current;
    }

    void switchPlayers() {
        this.activePlayer = this.getContrary();
    }

    public IPhase skipReaction(){
        this.skipAmount++;
        this.switchPlayers();
        if(this.skipAmount == 2){
            this.rstak.apply();
            return this.previous;
        }
        return this;
    }

    private void acceptReaction(CardInGame cig, Player target) {
        cig.reaction(this.activePlayer, target, this.rstak);
        switchPlayers();
    }

    @Override
    public Player activePlayer() {
        return this.activePlayer;
    }


    @Override
    public IPhase useReaction(CardInGame card, List<CardInGame> targets) {
        return this.useReaction(card, this.getContrary());
    }

    @Override
    public IPhase useReaction(ICard card, List<CardInGame> targets){
        CardInGame cig = this.zone.addCard(card, this.activePlayer);
        return this.useReaction(cig, this.getContrary());
    }

    @Override
    public IPhase useReaction(CardInGame card, Player target){
        this.acceptReaction(card, target);
        return this;
    }

    @Override
    public IPhase useReaction(ICard card, Player target) {
        CardInGame cig = this.zone.addCard(card, this.activePlayer);
        return this.useReaction(cig, target);
    }
}
