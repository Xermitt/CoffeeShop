package ru.daitbegov.CoffeeShop.service;

import org.springframework.stereotype.Component;
import ru.daitbegov.CoffeeShop.models.Product;
import ru.daitbegov.CoffeeShop.repository.ProductRepository;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void delete(int id){
        productRepository.deleteById(id);
    }
    public Product getProductById(Integer id) {
        return productRepository.getById(id);
    }
    public void updateById(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }

    public void decreaseProductCount(Integer productId, Integer amount){
        productRepository.decreaseProductCount(productId, amount);
    }
}
