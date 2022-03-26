package com.tim.backendapplication.product;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public List<ProductEntity> allProducts() {
        return productRepository.findAll();
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> productByID(@PathVariable int id) {
       return new ResponseEntity<>(productService.buildProductResponse(id), HttpStatus.OK);
    }

    @GetMapping("/jobid/{jobID}")
    public List<ProductDTO> jobID(@PathVariable int jobID) {
        return productService.findByJobID(jobID);
    }


}
