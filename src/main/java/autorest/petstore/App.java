package autorest.petstore;

import java.util.ArrayList;
import java.util.List;

import autorest.petstore.implementation.PetStoreClientImpl;
import autorest.petstore.models.Pet;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        // Create client instance.
        PetStoreClient client = new PetStoreClientImpl();

        // Find pets by status
        List<String> status = new ArrayList<String>();
        status.add("pending");
        List<Pet> pets = client.findPetsByStatus(status);
        for (Pet pet : pets) {
            System.out.println(pet.id() + " :  " + pet.name());
        }

        // Get pet by id
        Pet pet = client.getPetById(pets.get(0).id());
        System.out.println(pet.name());
        System.out.println(pet.status());
    }
}
