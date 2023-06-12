package com.core.g3.Match.GameMode;

import java.util.Optional;

import com.core.g3.Deck.IDeck;
import com.core.g3.Match.Player.Player;
import com.core.g3.User.User;

public interface IGameMode {
    public int CombatZoneLimit();

    public int ArtifactZoneLimit();

    public int ReserveZoneLimit();

    public Optional<Player> getWinner(Player player1, Player player2);

    public void drawInitialCards(Player player);

    public Player addPlayer(User user, IDeck deck);

    public void initialStage(Player player);
}