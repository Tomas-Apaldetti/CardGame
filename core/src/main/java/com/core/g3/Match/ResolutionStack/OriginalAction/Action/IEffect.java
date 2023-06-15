package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import java.util.List;

import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;

public interface IEffect {

    public List<ILingeringEffect> apply();
}
