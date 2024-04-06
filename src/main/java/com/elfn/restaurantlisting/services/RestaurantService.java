package com.elfn.restaurantlisting.services;

import com.elfn.restaurantlisting.dto.RestaurantDTO;
import com.elfn.restaurantlisting.entities.Restaurant;
import com.elfn.restaurantlisting.mappers.RestaurantMapper;
import com.elfn.restaurantlisting.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepo;

    public RestaurantService(RestaurantRepository restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }


    public List<RestaurantDTO> findAllRestaurants() {
        return restaurantRepo.findAll().stream()
                .map(RestaurantMapper.INSTANCE::restaurantToRestaurantDTO)
                .toList();
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.restaurantDTOToRestaurant(restaurantDTO);
            restaurantRepo.save(restaurant);
            return RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(restaurant);

    }

    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        if (restaurant.isPresent()) {
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.restaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
