package com.elfn.restaurantlisting.repositories;

import com.elfn.restaurantlisting.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
