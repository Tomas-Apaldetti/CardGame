package com.core.tcg.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Facade for the TCG game matches, to allow for multiple implementations.
 * <p>
 *
 * Reactions may only happen within reaction windows
 * (see {@link MatchDriver#startReactionWindow}),
 * otherwise methods should act as if both players refused to react.
 */
public interface MatchDriver<CardReference> {
    /**
     * @return The player's deck, where the Nth card is what they will draw
     *         in their Nth card draw
     */
    List<DriverCardName> deckOrder(DriverMatchSide player);

    /**
     * Reorders a player's deck, so their Nth card draw will get the Nth card
     * in the card list. Should only be called with a permutation of
     * the cards currently in the deck.
     */
    void forceDeckOrder(DriverMatchSide player, List<DriverCardName> cards);

    /**
     * Starts the match. Nothing should happen before this is called
     * (In particular: Initial card draw should happen after this, to
     * allow forceDeckOrder to change it)
     */
    void start();

    /**
     * End phases and turns until the current player and phase match the
     * arguments. Must end at least one phase
     */
    void skipToPhase(DriverMatchSide player, DriverTurnPhase phase);

    /**
     * Play one card from the player's hand into the given zone
     * 
     * @return Reference to the newly placed card
     * @throws RuntimeException if the summon can't complete as indicated
     */
    CardReference summon(DriverMatchSide player, DriverCardName card, DriverActiveZone zone);

    /**
     * @return Current hitpoints for the given card
     * @throws RuntimeException if the card is not a creature
     */
    int getCreatureHitpoints(CardReference card);

    /**
     * Perform the given creature's attack at index, against the target
     * 
     * @throws RuntimeException if the attack can't complete as indicated
     */
    void attackCreature(CardReference creature, int index, CardReference target);

    /**
     * Perform the given creature's attack at index, against the opposing player
     * 
     * @throws RuntimeException if the attack can't complete as indicated
     */
    void attackPlayer(CardReference creature, int index);

    /**
     * Activate an artifact in the player's active zones, supplying targets if
     * the specific card needs them
     * 
     * @throws RuntimeException if the artifact can't be activated as indicated
     */
    void activateArtifact(
            CardReference artifact,
            int index,
            Optional<DriverMatchSide> targetPlayer,
            List<CardReference> targets);

    default void activateArtifact(CardReference artifact) {
        activateArtifact(artifact, 0, Optional.empty(), new ArrayList<>());
    }

    /**
     * Play an action from the player's hand, supplying targets if the specific
     * card needs them
     * 
     * @throws RuntimeException if the action can't be activated as indicated
     */
    void activateAction(
            DriverMatchSide player,
            DriverCardName card,
            int index,
            Optional<DriverMatchSide> targetPlayer,
            List<CardReference> targetCards);

    /**
     * Start a reaction window, within which players may play reactions
     */
    void startReactionWindow();

    /**
     * End a reaction window, resolving and applying the combined effect
     */
    void endReactionWindow();

    default void withReactionWindow(Runnable actions) {
        try {
            startReactionWindow();
            actions.run();
        } catch (Throwable e) {
            endReactionWindow();
            throw e;
        }
    }

    /**
     * Play a reaction from the player's hand, supplying targets if the specific
     * card needs them
     * 
     * @throws RuntimeException if the reaction can't be activated as indicated
     */
    void activateReactionFromHand(
            DriverMatchSide player,
            DriverCardName card,
            Optional<DriverMatchSide> targetPlayer,
            List<CardReference> targetCards);

    /**
     * Play a reaction from the player's active zones, supplying targets if the
     * specific card needs them
     * 
     * @throws RuntimeException if the reaction can't be activated as indicated
     */
    void activateReactionFromActiveZone(
            CardReference card,
            Optional<DriverMatchSide> targetPlayer,
            List<CardReference> targetCards);

    /**
     * @return The player's current health
     * @throws RuntimeException if the player doesn't have health in this game mode
     */
    int playerHealth(DriverMatchSide player);

    /**
     * @return The player's current amount of an energy type
     */
    int playerEnergy(DriverMatchSide player, DriverEnergyType energyType);

    /**
     * @return The match's winner, if it has ended
     */
    Optional<DriverMatchSide> winner();
}
