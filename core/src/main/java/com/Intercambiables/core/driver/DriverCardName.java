package com.Intercambiables.core.driver;

import java.util.List;
import java.util.stream.Stream;

public enum DriverCardName {
    WaterEnergy,
    FireEnergy,
    PlantEnergy,
    Alchemist,
    Antimagic,
    MagicBarrier,
    Corrosion,
    Drain,
    MagicSword,
    Goblin,
    Hospital,
    BlockReaction,
    Inventor,
    Orc,
    Recycle,
    Resonance,
    Saboteur,
    Sacrifice,
    MagicDrill,
    Treason;

    /**
     * @return A list of length count, starting with prefix, followed by the
     *         full card list looped as necessary
     */
    public static List<DriverCardName> loopedCardNames(int count, Stream<DriverCardName> prefix) {
        DriverCardName[] arr = DriverCardName.values();
        Stream<DriverCardName> loopedArr = Stream
                .iterate(0, i -> (i + 1) % arr.length)
                .map(i -> arr[i]);
        return Stream.concat(prefix, loopedArr).limit(count).toList();
    }

    public static List<DriverCardName> loopedCardNames(int count) {
        return loopedCardNames(count, Stream.of());
    }
}
