package com.core.apirest.model;

import com.core.g3.Deck.ICard;

public class CardInGameInformation {
    public String cardName;

    public static CardInGameInformation fromCard(ICard card) {
        CardInGameInformation cardInGameInformation = new CardInGameInformation();
        cardInGameInformation.cardName = card.getName().toString();
        return cardInGameInformation;
    }
}
