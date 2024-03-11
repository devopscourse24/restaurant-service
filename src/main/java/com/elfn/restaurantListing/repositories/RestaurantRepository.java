package com.elfn.restaurantListing.repositories;

import com.elfn.restaurantListing.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
