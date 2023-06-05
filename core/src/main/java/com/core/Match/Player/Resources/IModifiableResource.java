package com.core.Match.Player.Resources;

import com.core.Commons.Amount;

public interface IModifiableResource extends IResource {
    void add(Amount value);

    IResource consume(Amount value);
}
