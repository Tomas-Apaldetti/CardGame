package com.core.g3.Match.GameMode;

import com.core.g3.Commons.Amount;
import com.core.g3.Deck.Deck;
import com.core.g3.Deck.IDeck;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.DeckPlayable.IDeckPlayable;
import com.core.g3.Match.Player.MatchEndCondition.PlainHP;
import com.core.g3.Match.Player.Player;
import com.core.g3.User.User;

import java.util.Optional;


public class GameMode1 extends GameMode {
    private int combatZoneLimit = 5;
    private int artifactZoneLimit = 0;
    private int reserveZoneLimit = 5;
    private final int initialHandSize = 5;

    public GameMode1() {
        this.MAXIMUM_CARDS_PER_DECK = 60;
        this.MINIMUM_CARDS_PER_DECK = 40;
        this.MAXIMUM_REPEATED_CARDS = 3;
    }

    public Player addPlayer(User user, IDeck deck) {
        this.checkDecks(deck);
        this.checkRepeatedCards(deck, MAXIMUM_REPEATED_CARDS);

        DeckPlayable playableDeck = new DeckPlayable(deck);
        Player player = new Player(user, (IDeckPlayable) deck, new PlainHP(new Amount(20)));
        return player;
    }

    public void drawInitialCards(Player player) {
        for(int i = 0; i < initialHandSize; i++) {
            player.drawCard();
        }
    }

    public void initialStage(Player player) {
        player.drawCard();
    }

    public Optional<Player> getWinner(Player player1, Player player2) {
        if (player1.matchEndConditionMet()) {
            return Optional.of(player2);
        } else if (player2.matchEndConditionMet()) {
            return Optional.of(player1);
        }
        return Optional.empty();
    }

    // Modo 1
    // Mazos: 40 a 60 cartas, maximo 3 copias de cada carta
    // Cada jugador inicia con 20 puntos de vida, que pueden reducirse por ataques
    // directos

    // Antes del primer turno: Cada jugador toma 5 cartas de su mazo
    // Etapa inicial: Toma una carta del mazo
    // Máximo 5 criaturas en zona de combate, 0 en zona de reserva, 5 artefactos
    // El primer jugador en llegar a 0 o menos puntos de vida pierde

}