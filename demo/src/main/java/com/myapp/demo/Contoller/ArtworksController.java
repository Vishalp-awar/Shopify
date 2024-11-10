package com.myapp.demo.Contoller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.demo.Model.artworks;
import com.myapp.demo.Repository.ArtworksRepository;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/artwroks")
@CrossOrigin(origins = "http://localhost:3000") 
public class ArtworksController {


    @Autowired
    private ArtworksRepository artworksRepository;

    @GetMapping("/allartworks")
    public List<artworks> getAllArtworks() {
        return artworksRepository.findAll();
    }
  
    @GetMapping("/getOneArtwork/{id}")
    public ResponseEntity<artworks> getOneArtwork(@PathVariable String id) {
        try {
            // Convert the String id to ObjectId
            ObjectId objectId = new ObjectId(id);

            // Use the repository's findById method (adjusted for ObjectId)
            Optional<artworks> artwork = artworksRepository.findById(objectId);

            // Check if the artwork is present
            if (artwork.isPresent()) {
                return ResponseEntity.ok(artwork.get()); // Return the artwork if found
            } else {
                return ResponseEntity.notFound().build(); // Return 404 if not found
            }

        } catch (IllegalArgumentException e) {
            // Return bad request if the ID format is invalid (not a valid ObjectId)
            return ResponseEntity.badRequest().body(null);
        }
    }



  
}