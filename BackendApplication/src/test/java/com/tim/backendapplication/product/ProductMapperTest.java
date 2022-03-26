package com.tim.backendapplication.product;

import com.tim.backendapplication.position.PositionDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void mapToDto() {
        ProductMapper underTest = new ProductMapper();
        ProductEntity entity = new ProductEntity();
        entity.setLocationID(5);
        entity.setDescription("aDescription");
        ProductDTO result = underTest.mapToDto(entity, "lager_test", "category_test");
        assertEquals(entity.getDescription(), result.getDescription());
        assertEquals(new PositionDTO(entity.getLocationID(), "lager_test"), result.getPosition());
    }
}