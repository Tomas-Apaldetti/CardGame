package com.core.apirest.model;

import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;

public class CardInGameInformation {
    public String cardName;
    public int hp;
    public String zone;

    public static CardInGameInformation fromCard(ICard card) {
        CardInGameInformation cardInGameInformation = new CardInGameInformation();
        cardInGameInformation.cardName = card.getName().toString();
        cardInGameInformation.hp = -1;
        cardInGameInformation.zone = null;
        return cardInGameInformation;
    }

    public static CardInGameInformation fromCard(CardInGame cardInGame) {
        CardInGameInformation cardInGameInformation = new CardInGameInformation();
        cardInGameInformation.cardName = cardInGame.getBase().getName().toString();
        cardInGameInformation.zone = cardInGame.getCurrentZone().getType().toString();
        try {
            cardInGameInformation.hp = cardInGame.getHealth();
        } catch (Exception e) {
            cardInGameInformation.hp = -1;
        }
        return cardInGameInformation;
    }
}
