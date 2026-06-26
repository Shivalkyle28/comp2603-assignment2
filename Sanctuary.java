import java.util.ArrayList;

/**
 * Manages a collection of animals at a single location.
 */
public class Sanctuary {
    // TODO M5: Declare private fields: name (String), island (String),
    //          capacity (int), animals (ArrayList<Animal>)
    private String name;
    private String island;
    private int capacity;
    private ArrayList<Animal> animals;

    /**
     * TODO M5: Implement constructor
     */
    public Sanctuary(String name, String island, int capacity) {
        // TODO M5: Initialize all fields, create empty ArrayList
        this.name = name;
        this.island = island;
        this.capacity = capacity;
        this.animals = new ArrayList<Animal>();
    }

    // TODO M5: Write getters for name, island, capacity, and animals
    public String getName() {
        return name;
    }

    public String getIsland() {
        return island;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    /**
     * Adds an animal to this sanctuary.
     * Rejects null animals, rejects if at capacity, rejects if animal's island
     * does not match this sanctuary's island.
     *
     * TODO M5: Implement addAnimal
     */
    public boolean addAnimal(Animal a) {
        // TODO M5: Validate and add
        if (a == null) {
            return false;
        }

        if (animals.size() >= capacity) {
            return false;
        }

        if (!a.getIsland().equals(island)) {
            return false;
        }

        animals.add(a);
        return true;
    }

    /**
     * Removes and returns the animal with the given ID, or null if not found.
     *
     * TODO M5: Implement removeAnimal
     */
    public Animal removeAnimal(int animalId) {
        // TODO M5: Find by ID, remove, and return
        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);

            if (a.getAnimalId() == animalId) {
                animals.remove(i);
                return a;
            }
        }

        return null;
    }

    /**
     * BONUS 47:
     * Finds and returns the animal with the specified ID.
     * Returns null if no animal with that ID exists.
     */
    public Animal findAnimalById(int id) {

        // BONUS 47:
        // Search through every animal in the sanctuary.
        for (Animal animal : animals) {

            // Check whether this animal has the requested ID.
            if (animal.getAnimalId() == id) {
                return animal;
            }
        }

        // No matching animal was found.
        return null;
    }
    /**
     * TODO M5: Implement getAnimalCount
     */
    public int getAnimalCount() {
        // TODO M5
        return animals.size();
    }

    /**
     * Returns a new ArrayList containing only animals of the given type.
     *
     * TODO M7: Implement getAnimalsOfType
     */
    public ArrayList<Animal> getAnimalsOfType(String type) {
        // TODO M7: Filter by getType()
        ArrayList<Animal> filtered = new ArrayList<Animal>();

        for (Animal a : animals) {
            if (a.getType().equals(type)) {
                filtered.add(a);
            }
        }

        return filtered;
    }

    /**
     * Returns the total daily food cost for all animals, rounded to 2 decimal places.
     *
     * TODO M7: Implement getDailyFoodBudget
     */
    public double getDailyFoodBudget() {
        // TODO M7: Sum getDailyFoodCostTTD() for all animals
        double total = 0.0;

        for (Animal a : animals) {
            total += a.getDailyFoodCostTTD();
        }

        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * Returns all animals that implement the Relocatable interface.
     * Hint: use instanceof.
     *
     * TODO M8: Implement getRelocatableAnimals
     */
    public ArrayList<Animal> getRelocatableAnimals() {
        // TODO M8: Filter using instanceof Relocatable
        ArrayList<Animal> relocatableAnimals = new ArrayList<Animal>();

        for (Animal a : animals) {
            if (a instanceof Relocatable) {
                relocatableAnimals.add(a);
            }
        }

        return relocatableAnimals;
    }

    /**
     * Returns the animal with the highest daily food cost, or null if empty.
     *
     * TODO M7: Implement getMostExpensiveAnimal
     */
    public Animal getMostExpensiveAnimal() {
        // TODO M7: Find max by getDailyFoodCostTTD()
        if (animals.isEmpty()) {
            return null;
        }

        Animal mostExpensive = animals.get(0);

        for (Animal a : animals) {
            if (a.getDailyFoodCostTTD() > mostExpensive.getDailyFoodCostTTD()) {
                mostExpensive = a;
            }
        }

        return mostExpensive;
    }

    /**
     * Transfers an animal to another sanctuary.
     * If the animal does not implement Relocatable, the transfer fails:
     * re-add the animal to this sanctuary and return false.
     * Otherwise, call relocateTo on the animal, then addAnimal on target.
     *
     * TODO M8: Implement transferAnimal
     */
    public boolean transferAnimal(int animalId, Sanctuary target) {
        // TODO M8: Remove animal, check Relocatable, relocate, add to target
        if (target == null) {
            return false;
        }

        Animal animal = removeAnimal(animalId);

        if (animal == null) {
            return false;
        }

        if (!(animal instanceof Relocatable)) {
            animals.add(animal);
            return false;
        }

        Relocatable relocatableAnimal = (Relocatable) animal;
        String originalIsland = this.island;

        relocatableAnimal.relocateTo(target.getIsland());

        if (!target.addAnimal(animal)) {
            relocatableAnimal.relocateTo(originalIsland);
            animals.add(animal);
            return false;
        }

        return true;
    }

    /**
     * Prints each animal's toString, indented by 2 spaces.
     *
     * TODO M5: Implement printRoster
     */
    public void printRoster() {
        // TODO M5: Loop and print
        for (Animal a : animals) {
            System.out.println("  " + a.toString());
        }
    }

    /**
     * Format: "Name (Island) [count/capacity animals]"
     * Example: "Caroni Bird Sanctuary (Trinidad) [12/50 animals]"
     *
     * TODO M5: Implement toString
     */
    @Override
    public String toString() {
        // TODO M5: Return formatted string
        return String.format("%s (%s) [%d/%d animals]",
                name, island, animals.size(), capacity);
    }
}
