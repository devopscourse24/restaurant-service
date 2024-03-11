package com.elfn.restaurantListing.bootstrap;

import com.elfn.restaurantListing.entities.Restaurant;
import com.elfn.restaurantListing.repositories.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    final RestaurantRepository restaurantRepository;

    public DataLoader(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void run(String... args)  {

            loadData();

    }

    private void loadData(){

// Initialisation des restaurants
        Restaurant antoinette = new Restaurant(1, "Chez Antoinette", "Rue des Jardins, Cocody", "Abidjan", "Une expérience authentique de la cuisine ivoirienne avec une touche moderne");
        Restaurant bistroLePatriote = new Restaurant(2, "Le Patriote", "Avenue Franchet d'Esperey, Treichville", "Abidjan", "Dégustez les saveurs locales dans une ambiance conviviale et chaleureuse.");
        Restaurant cafeDeLaPaix = new Restaurant(3, "Café de la Paix", "Boulevard de la République, Plateau", "Abidjan", "Un café historique offrant des pâtisseries fines et un café d'exception.");
        Restaurant laPerleNoire = new Restaurant(4, "La Perle Noire", "Rue Louis Lumière, Marcory", "Abidjan", "Un voyage culinaire qui met en avant la richesse des produits de la mer.");
        Restaurant leJardinGourmand = new Restaurant(5, "Le Jardin Gourmand", "Rue des Flamboyants, Deux Plateaux", "Abidjan", "Un cadre idyllique pour une cuisine innovante et raffinée.");
        Restaurant lEpicurien = new Restaurant(6, "L'Épicurien", "Avenue Noguès, Le Plateau", "Abidjan", "L'excellence de la gastronomie française au cœur d'Abidjan.");

        restaurantRepository.save(antoinette);
        restaurantRepository.save(bistroLePatriote);
        restaurantRepository.save(cafeDeLaPaix);
        restaurantRepository.save(laPerleNoire);
        restaurantRepository.save(leJardinGourmand);
        restaurantRepository.save(lEpicurien);

    }
}
