package com.aja;

import com.aja.mongo.dao.CategoryRepository;
import com.aja.mongo.dao.ProductReposiory;
import com.aja.mongo.entities.Category;
import com.aja.postgres.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class CatalogueServiceApplication implements CommandLineRunner {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(CatalogueServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        repositoryRestConfiguration.exposeIdsFor(Produit.class);
//
//        produitRepository.save(new Produit(null,"MacBook Pro retina",3000,3));
//        produitRepository.save(new Produit(null,"Iphone X 64",1100,3));
//        produitRepository.save(new Produit(null,"Ordinateur Lx 45",670,3));
//
//        //produitRepository.deleteAll();
//
//        produitRepository.findAll().forEach(p-> {
//            System.out.println(p.toString());
//        });


    }

    @Bean
    CommandLineRunner start(CategoryRepository categoryRepository, ProductReposiory productReposiory){
        return args -> {
            categoryRepository.deleteAll();
            Stream.of("C1 Ordinateurs","C2 Imprimantes").forEach(c-> {
                categoryRepository.save(new Category(c.split(" ")[0],c.split(" ")[1], new ArrayList<>()));
            });
            categoryRepository.findAll().forEach(System.out::println);
        };
    }
}
