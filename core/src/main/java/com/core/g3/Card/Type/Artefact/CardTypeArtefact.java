package com.core.g3.Card.Type.Artefact;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.core.g3.Card.Artefact.IArtefactEffect;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.Zone.ActiveZoneType;

public class CardTypeArtefact extends CardType {

    private final IArtefactEffect effect;
    protected List<ActiveZoneType> allowedZones;

    public CardTypeArtefact(IArtefactEffect effect) {
        super(ICardType.CardType.Artefact, Arrays.asList(ActiveZoneType.Artefacts));
        this.effect = effect;
    }

    public CardTypeArtefact(IArtefactEffect effect, List<ActiveZoneType> allowedZones) {
        super(ICardType.CardType.Artefact, allowedZones);
        this.effect = effect;
    }

    public Optional<IArtefactEffect> getEffects() {
        return Optional.ofNullable(effect);
    }

    @Override
    public boolean isArtefact() {
        return true;
    }

    @Override
    public OriginalAction artefact(OriginalAction action, Player user, Player rival) {
        return this.effect.apply(action, user, rival);
    }

    @Override
    public OriginalAction artefact(OriginalAction action, IAttackable affected, Player user, Player rival) {
        return this.effect.apply(action, affected, user, rival);
    }
}
