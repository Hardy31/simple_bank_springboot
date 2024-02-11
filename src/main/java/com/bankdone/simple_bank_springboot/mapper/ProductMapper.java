package com.bankdone.simple_bank_springboot.mapper;

import com.bankdone.simple_bank_springboot.dto.ProductCreateDTO;
import com.bankdone.simple_bank_springboot.dto.ProductDTO;
import com.bankdone.simple_bank_springboot.entity.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",  imports = LocalDateTime.class)
public interface ProductMapper {
    Product  convertToEntity(ProductDTO productDTO);

    @Mapping(target = "managerId", source = "product.manager.id")
    ProductDTO convertToDTO(Product product);

    List<ProductDTO> convertToProductDTOList(List<Product> Products);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Product CreateToEntity(ProductCreateDTO productCreateDTO);

}
