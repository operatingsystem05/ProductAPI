package com.example.demo.service;

import com.example.demo.dto.ProductCreateDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper)
    {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponseDTO getProductById(Long id)
    {
        Product product = productRepository.findById(id).orElse(null);
        try
        {
            if (product == null)
            {
                throw  new ProductNotFoundException("Product with id " + id + " not found");
            }
        }
        catch (ProductNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
        return productMapper.convertFromEntityToResponseDto(product);
    }

    public ProductResponseDTO createProduct(ProductCreateDTO dto)
    {
        Product product = productMapper.convertFromCreateDtoToEntity(dto);
        Product savedProduct = productRepository.save(product);
        return productMapper.convertFromEntityToResponseDto(savedProduct);
    }
}
