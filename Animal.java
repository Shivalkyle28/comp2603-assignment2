import java.util.ArrayList;

/**
 * Abstract base class for all animals in the conservation system.
 */
public abstract class Animal {
    // TODO M1: Declare static nextId field, starting at 1
    private static int nextId = 1;

    // TODO M1: Declare private fields:
    //   animalId (int), species (String), nickname (String),
    //   island (String), weightKg (double), healthStatus (String)
    private int animalId;
    private String species;
    private String nickname;
    private String island;
    private double weightKg;
    private String healthStatus;

    // TODO M4: Declare private ArrayList<String> sightings field
    private ArrayList<String> sightings;

    /**
     * Constructor: assigns auto-incremented ID, validates all parameters.
     * Species, nickname, island must not be null or empty.
     * weightKg must be > 0.
     * healthStatus must be "Healthy", "Injured", or "Critical".
     *
     * TODO M1: Implement constructor with validation
     * TODO M4: Initialize sightings list
     */
    public Animal(String species, String nickname, String island, double weightKg, String healthStatus) {
        // TODO M1: Validate parameters and assign fields
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("Species cannot be null or empty");
        }

        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname cannot be null or empty");
        }

        if (island == null || island.trim().isEmpty()) {
            throw new IllegalArgumentException("Island cannot be null or empty");
        }

        if (weightKg <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }

        if (!healthStatus.equals("Healthy")
                && !healthStatus.equals("Injured")
                && !healthStatus.equals("Critical")) {
            throw new IllegalArgumentException("Invalid health status");
        }

        this.species = species;
        this.nickname = nickname;
        this.island = island;
        this.weightKg = weightKg;
        this.healthStatus = healthStatus;

        // TODO M1: Auto-assign animalId from nextId, then increment nextId
        animalId = nextId++;

        // TODO M4: Initialize sightings ArrayList
        sightings = new ArrayList<String>();
    }

    // TODO M1: Write getters for all fields (getAnimalId, getSpecies, getNickname,
    //          getIsland, getWeightKg, getHealthStatus)

    public int getAnimalId() {
        return animalId;
    }

    public String getSpecies() {
        return species;
    }

    public String getNickname() {
        return nickname;
    }

    public String getIsland() {
        return island;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    // TODO M2: Write setIsland(String island) method
    public void setIsland(String island) {
        this.island = island;
    }

    // TODO M4: Write getSightings() getter that returns the ArrayList<String>
    protected ArrayList<String> getSightings() {
        return sightings;
    }

    /**
     * Updates the health status after validation.
     * TODO M1: Implement updateHealth
     */
    public void updateHealth(String newStatus) {
        // TODO M1: Validate newStatus and update the field
        if (!newStatus.equals("Healthy")
                && !newStatus.equals("Injured")
                && !newStatus.equals("Critical")) {
            throw new IllegalArgumentException("Invalid health status");
        }

        healthStatus = newStatus;
    }

    /**
     * Returns the animal type: "Bird", "Reptile", or "Marine".
     * TODO M2: Declare as abstract
     */
    public abstract String getType();

    /**
     * Returns the daily food cost in TTD. Varies by subclass.
     * TODO M2: Declare as abstract
     */
    public abstract double getDailyFoodCostTTD();

    /**
     * Format: "#%03d %s '%s' (%s) [%s] %.2f kg - %s"
     * Example: "#001 Scarlet Ibis 'Ruby' (Trinidad) [Bird] 0.35 kg - Healthy"
     *
     * TODO M1: Implement toString
     */
    @Override
    public String toString() {
        // TODO M1: Return formatted string
        return String.format("#%03d %s '%s' (%s) [%s] %.2f kg - %s",
                animalId,
                species,
                nickname,
                island,
                getType(),
                weightKg,
                healthStatus);
    }

    /**
     * Two animals are equal if they have the same animalId.
     *
     * TODO M5: Implement equals
     */
    @Override
    public boolean equals(Object obj) {
        // TODO M5: Implement equality by animalId
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Animal)) {
            return false;
        }

        Animal other = (Animal) obj;
        return animalId == other.animalId;
    }

    /**
     * TODO M5: Implement hashCode based on animalId
     */
    @Override
    public int hashCode() {
        // TODO M5: Return hash based on animalId
        return Integer.hashCode(animalId);
    }
}