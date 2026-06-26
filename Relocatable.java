/**
 * Relocatable interface for animals that can be transferred between islands.
 *
 * TODO M3: Define three method signatures:
 *   - boolean canRelocateTo(String targetIsland)
 *   - double getRelocationCost()
 *   - void relocateTo(String island)
 */
public interface Relocatable {
    // TODO M3: Declare canRelocateTo method
    boolean canRelocateTo(String targetIsland);

    // TODO M3: Declare getRelocationCost method
    double getRelocationCost();

    // TODO M3: Declare relocateTo method
    void relocateTo(String island);
}