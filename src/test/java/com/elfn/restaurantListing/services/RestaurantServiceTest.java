package com.elfn.restaurantListing.services;

import com.elfn.restaurantListing.dto.RestaurantDTO;
import com.elfn.restaurantListing.entities.Restaurant;
import com.elfn.restaurantListing.mappers.RestaurantMapper;
import com.elfn.restaurantListing.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    @Mock
    RestaurantRepository restaurantRepo;

    @InjectMocks
    RestaurantService restaurantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); //in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRestaurants() {
        // Create mock restaurants
        List<Restaurant> mockRestaurants = Arrays.asList(
                new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new Restaurant(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantRepo.findAll()).thenReturn(mockRestaurants);

        // Call the service method
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurants();

        // Verify the result
        assertEquals(mockRestaurants.size(), restaurantDTOList.size());
        for (int i = 0; i < mockRestaurants.size(); i++) {
            RestaurantDTO expectedDTO = RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(mockRestaurants.get(i));
            assertEquals(expectedDTO.getName(), restaurantDTOList.get(i).getName());
        }

        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).findAll();
    }

    @Test
    public void testAddRestaurantInDB() {
        // Create a mock restaurant to be saved
        RestaurantDTO mockRestaurantDTO = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        Restaurant mockRestaurant = RestaurantMapper.INSTANCE.restaurantDTOToRestaurant(mockRestaurantDTO);

        // Mock the repository behavior
        when(restaurantRepo.save(any(Restaurant.class))).thenReturn(mockRestaurant);

        // Call the service method
        RestaurantDTO savedRestaurantDTO = restaurantService.addRestaurantInDB(mockRestaurantDTO);

        // Verify the result
        assertEquals(mockRestaurantDTO.getName(), savedRestaurantDTO.getName());

        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testFetchRestaurantById_ExistingId() {
        // Create a mock restaurant ID
        Integer mockRestaurantId = 1;

        // Create a mock restaurant to be returned by the repository
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");

        // Mock the repository behavior
        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));

        // Call the service method
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurantId, response.getBody().getId());

        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).findById(mockRestaurantId);
    }

    @Test
    public void testFetchRestaurantById_NonExistingId() {
        // Create a mock non-existing restaurant ID
        Integer mockRestaurantId = 1;

        // Mock the repository behavior
        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.empty());

        // Call the service method
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());

        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).findById(mockRestaurantId);
    }

    @Test
    public void testAddRestaurantInDBWhenDbIsDown() {
        // Créer un DTO de restaurant factice pour l'ajout
        RestaurantDTO newRestaurantDTO = new RestaurantDTO(0, "Nouveau Restaurant", "Nouvelle Adresse", "Nouvelle Ville", "Nouvelle Description");

        // Simuler un comportement de la base de données échouant lors de l'opération de sauvegarde
        when(restaurantRepo.save(any(Restaurant.class))).thenThrow(new RuntimeException("Accès à la base de données impossible"));

        // Essayer d'appeler la méthode du service et vérifier qu'une exception est levée
        Exception exception = assertThrows(RuntimeException.class, () -> {
            restaurantService.addRestaurantInDB(newRestaurantDTO);
        });

        // Vérifier le message de l'exception
        assertEquals("Accès à la base de données impossible", exception.getMessage());

        // Vérifier que la méthode du repository a bien été appelée
        verify(restaurantRepo, times(1)).save(any(Restaurant.class));
    }
}