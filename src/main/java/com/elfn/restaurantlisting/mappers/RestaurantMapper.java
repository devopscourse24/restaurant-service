package com.elfn.restaurantlisting.mappers;

import com.elfn.restaurantlisting.dto.RestaurantDTO;
import com.elfn.restaurantlisting.entities.Restaurant;
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
