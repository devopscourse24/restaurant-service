package com.elfn.restaurantlisting.bootstrap;

import com.elfn.restaurantlisting.entities.Restaurant;
import com.elfn.restaurantlisting.repositories.RestaurantRepository;
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
        // Initialisation des restaurants
        Restaurant antoinette = new Restaurant(1152, "Chez Antoinette", "Rue des Jardins, Cocody", "Yamoussoukro", "Une expérience authentique de la cuisine ivoirienne avec une touche moderne");
        Restaurant bistroLePatriote = new Restaurant(1153, "Le Patriote", "Avenue Franchet d'Esperey, Treichville", "San-Pédro", "Dégustez les saveurs locales dans une ambiance conviviale et chaleureuse.");
        Restaurant cafeDeLaPaix = new Restaurant(1154, "Café de la Paix", "Boulevard de la République, Plateau", "Bouaké", "Un café historique offrant des pâtisseries fines et un café d'exception.");
        Restaurant laPerleNoire = new Restaurant(1155, "La Perle Noire", "Rue Louis Lumière, Marcory", "Daloa", "Un voyage culinaire qui met en avant la richesse des produits de la mer.");
        Restaurant leJardinGourmand = new Restaurant(1156, "Le Jardin Gourmand", "Rue des Flamboyants, Deux Plateaux", "Korhogo", "Un cadre idyllique pour une cuisine innovante et raffinée.");
        Restaurant lEpicurien = new Restaurant(1157, "L'Épicurien", "Avenue Noguès, Le Plateau", "Man", "L'excellence de la gastronomie française au cœur d'Abidjan.");

        restaurantRepository.save(antoinette);
        restaurantRepository.save(bistroLePatriote);
        restaurantRepository.save(cafeDeLaPaix);
        restaurantRepository.save(laPerleNoire);
        restaurantRepository.save(leJardinGourmand);
        restaurantRepository.save(lEpicurien);

    }
}
