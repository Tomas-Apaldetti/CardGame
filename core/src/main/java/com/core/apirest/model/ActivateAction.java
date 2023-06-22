package com.core.apirest.model;

import java.util.List;

public class ActivateAction {
    public String action;
    public int idx;
    public String playerTarget;
    public List<CardTarget> cardsTarget;

    public ActivateAction() {
        this.action = null;
        this.idx = 0;
        this.cardsTarget = null;
    }
}
