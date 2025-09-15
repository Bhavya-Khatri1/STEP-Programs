public class VirtualPetDemo {
    public static void main(String[] args) {
        PetSpecies dragon = new PetSpecies("Dragon", new String[]{"Egg", "Hatchling", "Elder"}, 100, "Cave");
        VirtualPet pet = new VirtualPet("Smoky", dragon);

        System.out.println(pet);
        pet.feedPet("Meat");
        pet.playWithPet("Fetch Fireball");
        System.out.println(pet);
    }
}