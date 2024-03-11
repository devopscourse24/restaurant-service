package com.elfn.restaurantListing.mappers;

import com.elfn.restaurantListing.dto.RestaurantDTO;
import com.elfn.restaurantListing.entities.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(source = "name", target = "name")
    RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant);
    Restaurant restaurantDTOToRestaurant(RestaurantDTO restaurantDto);

}
