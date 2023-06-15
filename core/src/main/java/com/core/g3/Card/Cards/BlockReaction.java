package com.core.g3.Card.Cards;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Reaction.PreventReactionReaction;

public class BlockReaction {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.BlockReaction);

        builder.cardTypeBuilder.setTypeReaction(new PreventReactionReaction());

        return builder.build();
    }
}
