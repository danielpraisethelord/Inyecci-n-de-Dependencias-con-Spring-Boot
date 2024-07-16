package com.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.springboot.di.app.springboot_di.models.Product;
import com.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private Environment environment;

    /*
     * @Value("${config.price.tax}")
     * private Double tax;
     */

    @Autowired
    @Qualifier("productJson")
    private ProductRepository productRepository; // = new ProductRepositoryImpl();

    /*
     * @Autowired
     * public void setProductRepository(ProductRepository productRepository) {
     * this.productRepository = productRepository;
     * }
     */

    /*
     * public ProductServiceImpl(@Qualifier("productFoo") ProductRepository
     * productRepository) {
     * this.productRepository = productRepository;
     * }
     */

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * environment.getProperty("config.price.tax", Double.class);
            // Double priceTax = p.getPrice() * tax;
            // Product newProduct = new Product(p.getId(), p.getName(),
            // priceImp.longValue());

            Product newProduct = (Product) p.clone();
            newProduct.setPrice(priceTax.longValue());
            return newProduct;

            // p.setPrice(priceTax.longValue());
            // return p;
        }).collect(Collectors.toList());
    }

    public List<Product> findAll2() {
        return productRepository.findAll().stream()
                .map(p -> {
                    Product newProduct = (Product) p.clone();
                    newProduct.setPrice((long) (p.getPrice() * 1.25));
                    return newProduct;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

}
