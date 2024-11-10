package com.myapp.demo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.myapp.demo.Model.artworks;

public interface ArtworksRepository extends MongoRepository<artworks, ObjectId> {

}
