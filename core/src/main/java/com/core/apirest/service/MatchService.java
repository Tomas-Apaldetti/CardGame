package com.core.apirest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.apirest.model.CardInGameInformation;
import com.core.apirest.model.CardTarget;
import com.core.apirest.model.MatchInformation;
import com.core.apirest.model.PlayerMatchInformation;
import com.core.apirest.model.UserAPI;
import com.core.apirest.service.exceptions.MatchAlreadyStartedException;
import com.core.g3.Card.Card;
import com.core.g3.Card.CardFactory;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.Deck;
import com.core.g3.Deck.ICard;
import com.core.g3.Deck.IDeck;
import com.core.g3.Match.Match;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.GameMode.GameMode;
import com.core.g3.Match.GameMode.GameMode1;
import com.core.g3.Match.GameMode.GameMode2;
import com.core.g3.Match.Phase.PhaseType;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.g3.User.User;

@Component
public class MatchService {
    public int totalGames = 0;
    public List<Match> matches = new ArrayList<>();
    @Autowired
    private UserService userService;

    public int createForcedMatch(String bluePlayer, String greenPlayer, String gameMode) {
        GameMode newGameMode;
        if (gameMode.equals("HitPointLoss")) {
            newGameMode = new GameMode1();
        } else {
            newGameMode = new GameMode2();
        }

        User blueUser = userService.getUser(bluePlayer).user;
        User greeUser = userService.getUser(greenPlayer).user;

        // Create deck only for testing purposes
        Deck blueDeck = generateRandomDeck(40, 3);
        Deck greenDeck = generateRandomDeck(40, 3);
        // Create deck only for testing purposes

        Player newBluePlayer = newGameMode.addPlayer(blueUser, blueDeck);
        Player newGreenPlayer = newGameMode.addPlayer(greeUser, greenDeck);

        // Adding energies to players for testing purposes
        newBluePlayer.add(EnergyType.Fire, new Amount(100));
        newBluePlayer.add(EnergyType.Water, new Amount(100));
        newBluePlayer.add(EnergyType.Plant, new Amount(100));
        newGreenPlayer.add(EnergyType.Fire, new Amount(100));
        newGreenPlayer.add(EnergyType.Water, new Amount(100));
        newGreenPlayer.add(EnergyType.Plant, new Amount(100));
        newBluePlayer.getDeck().putCardOnTop(CardFactory.createCard(CardName.Alchemist));
        newGreenPlayer.getDeck().putCardOnTop(CardFactory.createCard(CardName.Goblin));
        newGreenPlayer.getDeck().putCardOnTop(CardFactory.createCard(CardName.Hospital));
        // Adding energies to players for testing purposes

        Match newMatch = new Match(newBluePlayer, newGreenPlayer, newGameMode);
        this.totalGames++;
        this.matches.add(newMatch);
        return this.totalGames;
    }

    public int createMatch(String bluePlayer, String greenPlayer, String gameMode, String blueDeckName,
            String greenDeckName) {
        GameMode newGameMode;
        if (gameMode.equals("HitPointLoss")) {
            newGameMode = new GameMode1();
        } else {
            newGameMode = new GameMode2();
        }

        UserAPI blueUser = userService.getUser(bluePlayer);
        UserAPI greenUser = userService.getUser(greenPlayer);

        IDeck blueDeck = userService.GetDeck(blueDeckName, blueUser);
        IDeck greenDeck = userService.GetDeck(greenDeckName, greenUser);

        Player newBluePlayer = newGameMode.addPlayer(blueUser.user, blueDeck);
        Player newGreenPlayer = newGameMode.addPlayer(greenUser.user, greenDeck);

        Match newMatch = new Match(newBluePlayer, newGreenPlayer, newGameMode);
        this.totalGames++;
        this.matches.add(newMatch);
        return this.totalGames;
    }

    public Match getMatch(int matchId) {
        return this.matches.get(matchId - 1);
    }

    public MatchInformation getMatchInformation(int matchId) {
        Match match = this.getMatch(matchId);
        MatchInformation matchInformation = MatchInformation.fromMatch(matchId, match);
        return matchInformation;
    }

    public MatchInformation startMatch(int matchId) {
        Match match = this.getMatch(matchId);
        if (!match.getPhaseType().equals(PhaseType.NotPlayable)) {
            throw new MatchAlreadyStartedException();
        }
        match.startMatch(PlayerZone.Blue);
        MatchInformation matchInformation = MatchInformation.fromMatch(matchId, match);
        return matchInformation;
    }

    public PlayerMatchInformation getPlayerMatchInformation(int matchId, PlayerZone playerZone) {
        Match match = this.getMatch(matchId);
        Player player = match.getPlayer(playerZone);
        PlayerMatchInformation playerMatchInformation = PlayerMatchInformation.fromPlayer(player);
        return playerMatchInformation;
    }

    public List<CardInGameInformation> getPlayerCardsInHand(int matchId, PlayerZone playerZone) {
        Match match = this.getMatch(matchId);
        Player player = match.getPlayer(playerZone);
        List<CardInGameInformation> cards = player.seeHand().stream().map(card -> CardInGameInformation.fromCard(card))
                .collect(Collectors.toList());
        return cards;
    }

    public List<CardInGameInformation> getCardsInActiveZones(int matchId, PlayerZone playerZone) {
        Match match = this.getMatch(matchId);
        Player player = match.getPlayer(playerZone);
        List<CardInGame> cardsInGame = player.getCardsInGame();
        List<CardInGameInformation> cards = new ArrayList<>();
        for (CardInGame cardInGame : cardsInGame) {
            CardInGameInformation cardInGameInformation = CardInGameInformation.fromCard(cardInGame);
            cards.add(cardInGameInformation);
        }
        return cards;
    }

    public String summonCard(int matchId, CardName cardName, ActiveZoneType zone) {
        Match match = this.getMatch(matchId);
        PlayerZone playerZone = match.currentActivePlayerZone();
        match.summon(playerZone, cardName, zone);
        return "Card summond";
    }

    public String skipToPhase(int matchId, PlayerZone playerZone, PhaseType phaseType) {
        Match match = this.getMatch(matchId);
        match.skipToPhase(playerZone, phaseType);
        return "Phase skipped";
    }

    public String attackPlayer(int matchId, CardName cardName, int idx) {
        Match match = this.getMatch(matchId);
        match.attackPlayer(cardName, idx);
        return "Player attacked";
    }

    public String attackCreature(int matchId, CardName cardName, int idx, CardName rivalCardName) {
        Match match = this.getMatch(matchId);
        Player player = match.currentActivePlayer();
        List<CardInGame> creatures = player.getCardsInZoneByCardName(cardName, ActiveZoneType.Combat);
        CardInGame card = creatures.get(0);

        Player rival = match.getRival(player);
        List<CardInGame> rivalCreatures = rival.getCardsByCardName(rivalCardName);
        CardInGame rivalCard = rivalCreatures.get(0);

        match.attackCreature(card.getBase(), idx, rivalCard.getBase());
        return "Creature attacked";
    }

    public String skipReaction(int matchId) {
        Match match = this.getMatch(matchId);
        match.skipReaction();
        return "Reaction phase skipped";
    }

    public String activateArtifact(int matchId, CardName cardName, int idx, Optional<PlayerZone> playerZone,
            List<CardTarget> cardsTargetList) {
        Match match = this.getMatch(matchId);
        List<ICard> cardsTarget = this.getCardsTarget(match, cardsTargetList);

        System.out.println("Card name to play: " + cardName);
        System.out.println("Cards target to activate artifact: " + cardsTarget);

        match.activateArtifact(cardName, idx, playerZone, cardsTarget);
        return "Artifact activated";
    }

    public String activateAction(int matchId, CardName cardName, int idx) {
        Match match = this.getMatch(matchId);
        PlayerZone playerZone = match.currentActivePlayerZone();
        match.activateAction(playerZone, cardName, idx, Optional.empty(), new ArrayList<>());
        return "Action activated";
    }

    private List<ICard> getCardsTarget(Match match, List<CardTarget> cardsTargetList) {
        List<CardInGame> cardsTarget = new ArrayList<>();
        for (CardTarget cardTarget : cardsTargetList) {
            Player player = match.getPlayer(PlayerZone.valueOf(cardTarget.cardPlayer));
            List<CardInGame> cardTargetsByCardName = match.getCardsByCardName(player,
                    CardName.valueOf(cardTarget.cardName), cardTarget.cardIdx);
            for (CardInGame cardInGame : cardTargetsByCardName) {
                cardsTarget.add(cardInGame);
            }
        }
        return cardsTarget.stream().map(card -> card.getBase()).collect(Collectors.toList());
    }

    // Create deck only for testing purposes
    public Deck generateRandomDeck(int numberOfCards, int numberPerCard) {
        Deck deck = new Deck("test_deck");
        for (CardName cardName : CardName.values()) {
            for (int i = 0; i < numberPerCard; i++) {
                Card card = CardFactory.createCard(cardName);
                deck.addCard(card);
                if (deck.getCards().size() == numberOfCards) {
                    return deck;
                }
            }
        }
        return deck;
    }
    // Create deck only for testing purposes
}
