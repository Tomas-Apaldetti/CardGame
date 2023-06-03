package com.Intercambiables.core.Match.Player.Resources;

import com.Intercambiables.core.Commons.Amount;

public interface IModifiableResource extends IResource {
    void add(Amount value);

    IResource consume(Amount value);
}
