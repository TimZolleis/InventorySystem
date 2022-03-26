package com.tim.backendapplication.product;

import com.tim.backendapplication.categories.CategoryEntity;
import com.tim.backendapplication.categories.CategoryRepository;
import com.tim.backendapplication.position.PositionEntity;
import com.tim.backendapplication.position.PositionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;

    public ProductService init() {
        return new ProductService(productRepository, categoryRepository, positionRepository, modelMapper);
    }

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, PositionRepository positionRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.positionRepository = positionRepository;
        this.modelMapper = modelMapper;
    }

    public ProductEntity getProduct(int id) {
        return productRepository.findById(id).orElseThrow(null);
    }

    public CategoryEntity getCategory(int id) {
        return categoryRepository.findById(id).orElseThrow(null);
    }

    public String getCategoryString(int id) {
        return categoryRepository.getById(id).getName();

    }

    public PositionEntity getPosition(int id) {
        return positionRepository.findById(id).orElseThrow(null);
    }

    public List<ProductDTO> findByJobID(int id) {
        List<ProductEntity> entities = productRepository.findByJobID(id);
        modelMapper.addMappings(productMap);
        return entities.stream().map(entity -> modelMapper.map(entity, ProductDTO.class)).collect(Collectors.toList());
    }


    public ProductDTO buildProductResponse(int id) {
        ProductDTO product = convertToProductDTO(getProduct(id));
        product.setCategory(getCategory(getProduct(id).getCategoryID()).getName());
        product.setPosition(getPosition(getProduct(id).getLocationID()).getName());
        return product;
    }

    private ProductDTO convertToProductDTO(ProductEntity entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

    PropertyMap<ProductEntity, ProductDTO> productMap = new PropertyMap<ProductEntity, ProductDTO>() {
        @Override
        protected void configure() {
            map().setCategory(getCategoryString(source.getCategoryID()));
            map().setPosition(getCategoryString(source.getLocationID()));
        }
    };

}
