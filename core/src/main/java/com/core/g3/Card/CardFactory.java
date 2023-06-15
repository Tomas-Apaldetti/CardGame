package com.core.g3.Card;

import com.core.g3.Card.Cards.Alchemist;
import com.core.g3.Card.Cards.FireEnergy;
import com.core.g3.Card.Cards.PlantEnergy;
import com.core.g3.Card.Cards.WaterEnergy;

public class CardFactory {
    public static Card createCard(CardName name) {
        // create a card based on the name from CardName
        switch (name) {
            case WaterEnergy:
                return WaterEnergy.create();
            case Alchemist:
                return Alchemist.create();
            case Antimagic:
                break;
            case BlockReaction:
                break;
            case Corrosion:
                break;
            case Drain:
                break;
            case FireEnergy:
                return FireEnergy.create();
            case Goblin:
                break;
            case Hospital:
                break;
            case Inventor:
                break;
            case MagicBarrier:
                break;
            case MagicDrill:
                break;
            case MagicSword:
                break;
            case Orc:
                break;
            case PlantEnergy:
                return PlantEnergy.create();
            case Recycle:
                break;
            case Resonance:
                break;
            case Saboteur:
                break;
            case Sacrifice:
                break;
            case Treason:
                break;
            default:
                break;
        }
        return null;
    }

}
