package com.example.demo.mapper;

import com.example.demo.dto.ProductCreateDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper
{
    public Product convertFromCreateDtoToEntity(ProductCreateDTO dto)
    {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return product;
    }

    public  ProductResponseDTO convertFromEntityToResponseDto(Product product)
    {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
