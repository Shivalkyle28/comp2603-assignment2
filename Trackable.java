/**
 * Trackable interface for animals that can have sighting logs.
 *
 * TODO M3: Define three method signatures:
 *   - void logSighting(String date, String location)
 *   - int getSightingCount()
 *   - String getLastSighting()
 */
public interface Trackable {
    // TODO M3: Declare logSighting method
    void logSighting(String date, String location);

    // TODO M3: Declare getSightingCount method
    int getSightingCount();

    // TODO M3: Declare getLastSighting method
    String getLastSighting();
}