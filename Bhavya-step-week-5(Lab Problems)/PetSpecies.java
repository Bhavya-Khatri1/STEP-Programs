final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isEmpty()) {
            throw new IllegalArgumentException("Species name cannot be empty");
        }
        if (evolutionStages == null || evolutionStages.length == 0) {
            throw new IllegalArgumentException("Evolution stages required");
        }
        if (maxLifespan <= 0) {
            throw new IllegalArgumentException("Invalid lifespan");
        }
        this.speciesName = speciesName;
        this.evolutionStages = evolutionStages.clone();
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String[] getEvolutionStages() {
        return evolutionStages.clone();
    }

    public int getMaxLifespan() {
        return maxLifespan;
    }

    public String getHabitat() {
        return habitat;
    }

    @Override
    public String toString() {
        return "PetSpecies: " + speciesName + ", habitat: " + habitat + ", max lifespan: " + maxLifespan;
    }
}