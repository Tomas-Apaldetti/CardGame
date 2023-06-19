package com.core.apirest.model;

import com.core.g3.Match.Match;
import com.core.g3.Match.Player.PlayerZone;

public class MatchInformation {
    public int matchId;
    public String gameMode;
    public String bluePlayer;
    public String greenPlayer;
    public String turn;
    public String phase;
    public int bluePlayerHitPoints;
    public int greenPlayerHitPoints;
    public String winner;

    public MatchInformation() {
        this.matchId = 0;
        this.gameMode = null;
        this.bluePlayer = null;
        this.greenPlayer = null;
        this.turn = null;
        this.phase = null;
        this.bluePlayerHitPoints = 0;
        this.greenPlayerHitPoints = 0;
        this.winner = null;
    }

    public static MatchInformation fromMatch(int id, Match match) {
        MatchInformation matchInformation = new MatchInformation();
        matchInformation.matchId = id;
        matchInformation.gameMode = match.gamemode().getGameModeType().toString();
        matchInformation.bluePlayer = match.getPlayer(PlayerZone.Blue).getUsername();
        matchInformation.greenPlayer = match.getPlayer(PlayerZone.Green).getUsername();
        try {
            matchInformation.turn = match.currentActivePlayer().getUsername();
        } catch (Exception e) {
            matchInformation.turn = null;
        }
        matchInformation.phase = match.getPhaseType().toString();
        matchInformation.bluePlayerHitPoints = match.playerHealth(PlayerZone.Blue);
        matchInformation.greenPlayerHitPoints = match.playerHealth(PlayerZone.Green);
        if (match.getWinner().isEmpty()) {
            matchInformation.winner = null;
        } else {
            matchInformation.winner = match.getPlayer(match.getWinner().get()).getUsername();
        }
        return matchInformation;
    }

}
