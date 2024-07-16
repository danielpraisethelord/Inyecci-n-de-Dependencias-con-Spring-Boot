package com.springboot.di.app.springboot_di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
// import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import com.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;

/**
 * Clase de configuración de Spring para definir beans.
 */
@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpath:json/product.json")
    private Resource resource;

    @Bean("productJson")
    // @Primary
    ProductRepository productRepositoryJson() {
        return new ProductRepositoryJson(resource);
    }
}
