package com.core.g3.Card.Type.Artifact;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.core.g3.Card.Artifact.IArtifactEffect;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.CardTypeName;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.Zone.ActiveZoneType;

public class CardTypeArtifact extends CardType {

    private final IArtifactEffect effect;
    protected List<ActiveZoneType> allowedZones;

    public CardTypeArtifact(IArtifactEffect effect) {
        super(CardTypeName.Artifact, Arrays.asList(ActiveZoneType.Artifacts));
        this.effect = effect;
    }

    public CardTypeArtifact(IArtifactEffect effect, List<ActiveZoneType> allowedZones) {
        super(CardTypeName.Artifact, allowedZones);
        this.effect = effect;
    }

    public Optional<IArtifactEffect> getEffects() {
        return Optional.ofNullable(effect);
    }

    @Override
    public boolean is(CardTypeName cardType) {
        return CardTypeName.Artifact == cardType;
    }

    @Override
    public OriginalAction artifact(OriginalAction action, Player user, Player rival) {
        return this.effect.apply(action, user, rival);
    }

    @Override
    public OriginalAction artifact(OriginalAction action, List<IAffectable> affected, Player user, Player rival) {
        return this.effect.apply(action, affected, user, rival);
    }
}
