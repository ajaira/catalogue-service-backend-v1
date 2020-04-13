package com.aja.mongo.dao;

import com.aja.mongo.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductReposiory extends MongoRepository<Product,String> {
}
