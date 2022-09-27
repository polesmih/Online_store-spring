package com.geekbrains.spring.web.cart.api;

import com.geekbrains.spring.web.cart.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "Core", url = "http://localhost:8189/web-market-core/api/v1")
public interface CartApi {

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    Page<ProductDto> getAllProducts();

    @RequestMapping(method = RequestMethod.GET, value = "products/{id}")
    ProductDto getProductById(@PathVariable Long id);
}