package com.aja.mongo;

import com.aja.mongo.dao.CategoryRepository;
import com.aja.mongo.dao.ProductReposiory;
import com.aja.mongo.entities.Category;
import com.aja.mongo.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class CatalogueServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CatalogueServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(CategoryRepository categoryRepository, ProductReposiory productReposiory) {
        return args -> {

            // categories
            categoryRepository.deleteAll();
            Stream.of("C1 Ordinateurs", "C2 Imprimantes").forEach(c -> {
                categoryRepository.save(new Category(c.split(" ")[0], c.split(" ")[1], new ArrayList<>()));
            });
            categoryRepository.findAll().forEach(System.out::println);

            // Products
            productReposiory.deleteAll();
            Category c1 = categoryRepository.findById("C1").get();
            Stream.of("P1", "P2", "P3", "P4").forEach(name -> {
                Product p1 = productReposiory.save(new Product(null, name, Math.random() * 100, c1));
                c1.getProducts().add(p1);
                categoryRepository.save(c1);
            });

            Category c2 = categoryRepository.findById("C2").get();
            Stream.of("P5", "P6", "P7", "P8").forEach(name -> {
                Product p2 = productReposiory.save(new Product(null, name, Math.random() * 100, c2));
                c2.getProducts().add(p2);
                categoryRepository.save(c2);
            });

            productReposiory.findAll().forEach(System.out::println);
        };
    }
}
