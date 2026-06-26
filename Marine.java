/**
 * Marine subclass. Implements Trackable and Relocatable.
 *
 * TODO M2: Make this class extend Animal
 * TODO M3: Make this class implement Trackable and Relocatable
 */
public class Marine extends Animal implements Trackable, Relocatable {
    // TODO M2: Declare private fields: maxDepthM (double), tankSizeLitres (int)
    private double maxDepthM;
    private int tankSizeLitres;

    /**
     * Constructor.
     * TODO M2: Implement constructor that calls super() and sets Marine-specific fields
     */
    public Marine(String species, String nickname, String island, double weightKg, String healthStatus,
                  double maxDepthM, int tankSizeLitres) {
        // TODO M2: Call super constructor
        super(species, nickname, island, weightKg, healthStatus);

        // TODO M2: Set maxDepthM and tankSizeLitres
        this.maxDepthM = maxDepthM;
        this.tankSizeLitres = tankSizeLitres;
    }

    // TODO M2: Write getMaxDepthM() and getTankSizeLitres() getters
    public double getMaxDepthM() {
        return maxDepthM;
    }

    public int getTankSizeLitres() {
        return tankSizeLitres;
    }

    /**
     * TODO M2: Implement getType() - returns "Marine"
     */
    @Override
    public String getType() {
        return "Marine";
    }

    /**
     * Daily food cost = 50.0 + weightKg * 3.0
     * TODO M2: Implement getDailyFoodCostTTD()
     */
    @Override
    public double getDailyFoodCostTTD() {
        return 50.0 + getWeightKg() * 3.0;
    }

    // --- Trackable methods ---

    // TODO M4: Implement logSighting(String date, String location)
    @Override
    public void logSighting(String date, String location) {
        getSightings().add(date + " at " + location);
    }

    // TODO M4: Implement getSightingCount()
    @Override
    public int getSightingCount() {
        return getSightings().size();
    }

    // TODO M4: Implement getLastSighting()
    @Override
    public String getLastSighting() {
        if (getSightings().isEmpty()) {
            return "No sightings recorded";
        }

        return getSightings().get(getSightings().size() - 1);
    }

    // --- Relocatable methods ---

    // TODO M6: Implement canRelocateTo(String targetIsland)
    //          Returns true only if targetIsland is NOT the animal's current island
    @Override
    public boolean canRelocateTo(String targetIsland) {
        return !getIsland().equals(targetIsland);
    }

    // TODO M6: Implement getRelocationCost()
    //          Returns 2000.0 + tankSizeLitres * 5.0
    @Override
    public double getRelocationCost() {
        return 2000.0 + tankSizeLitres * 5.0;
    }

    // TODO M6: Implement relocateTo(String island)
    //          Updates the island using setIsland()
    @Override
    public void relocateTo(String island) {
        setIsland(island);
    }
}