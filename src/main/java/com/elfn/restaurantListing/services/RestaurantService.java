package com.elfn.restaurantListing.services;

import com.elfn.restaurantListing.dto.RestaurantDTO;
import com.elfn.restaurantListing.entities.Restaurant;
import com.elfn.restaurantListing.mappers.RestaurantMapper;
import com.elfn.restaurantListing.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepo;

    public RestaurantService(RestaurantRepository restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }


    public List<RestaurantDTO> findAllRestaurants() {

        List<RestaurantDTO> restaurantDtos = restaurantRepo.findAll().stream().map(RestaurantMapper.INSTANCE::restaurantToRestaurantDTO).collect(Collectors.toList());

        return restaurantDtos;
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
