package com.core.g3.Match.Player.Resources;

import com.core.g3.Commons.Amount;

public interface IModifiableResource extends IResource {
    void add(Amount value);

    IResource consume(Amount value);
}
