package com.core.g3.Card;

import com.core.g3.Card.Cards.Alchemist;
import com.core.g3.Card.Cards.Antimagic;
import com.core.g3.Card.Cards.BlockReaction;
import com.core.g3.Card.Cards.Corrosion;
import com.core.g3.Card.Cards.Drain;
import com.core.g3.Card.Cards.FireEnergy;
import com.core.g3.Card.Cards.Goblin;
import com.core.g3.Card.Cards.Hospital;
import com.core.g3.Card.Cards.Inventor;
import com.core.g3.Card.Cards.MagicBarrier;
import com.core.g3.Card.Cards.MagicDrill;
import com.core.g3.Card.Cards.MagicSword;
import com.core.g3.Card.Cards.Orc;
import com.core.g3.Card.Cards.PlantEnergy;
import com.core.g3.Card.Cards.Recycle;
import com.core.g3.Card.Cards.Resonance;
import com.core.g3.Card.Cards.Saboteur;
import com.core.g3.Card.Cards.Treason;
import com.core.g3.Card.Cards.WaterEnergy;

public class CardFactory {
    public static Card createCard(CardName name) {
        switch (name) {
            case WaterEnergy:
                return WaterEnergy.create();
            case Alchemist:
                return Alchemist.create();
            case Antimagic:
                return Antimagic.create();
            case BlockReaction:
                return BlockReaction.create();
            case Corrosion:
                return Corrosion.create();
            case Drain:
                return Drain.create();
            case FireEnergy:
                return FireEnergy.create();
            case Goblin:
                return Goblin.create();
            case Hospital:
                return Hospital.create();
            case Inventor:
                return Inventor.create();
            case MagicBarrier:
                return MagicBarrier.create();
            case MagicDrill:
                return MagicDrill.create();
            case MagicSword:
                return MagicSword.create();
            case Orc:
                return Orc.create();
            case PlantEnergy:
                return PlantEnergy.create();
            case Recycle:
                return Recycle.create();
            case Resonance:
                return Resonance.create();
            case Saboteur:
                return Saboteur.create();
            case Sacrifice:
                break;
            case Treason:
                return Treason.create();
            default:
                break;
        }
        return null;
    }

}
