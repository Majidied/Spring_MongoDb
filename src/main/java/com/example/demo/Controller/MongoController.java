package com.example.demo.Controller;

import com.example.demo.Service.MongoService;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MongoController {
    private final MongoService mongoService;

    public MongoController(MongoService mongoService) {
        this.mongoService = mongoService;
    }

    // 1️⃣ Afficher toutes les bases de données
    @GetMapping("/databases")
    public List<String> getDatabases() {
        return mongoService.listDatabases();
    }

    // 2️⃣ Créer une base de données
    @PostMapping("/databases/create/{dbName}")
    public String createDatabase(@PathVariable String dbName) {
        return mongoService.createDatabase(dbName);
    }

    // Supprimer une base de données
    @DeleteMapping("/databases/delete/{dbName}")
    public String deleteDatabase(@PathVariable String dbName) {
        return mongoService.deleteDatabase(dbName);
    }

    // 3️⃣ Afficher toutes les collections d'une BD
    @GetMapping("/collections/{dbName}")
    public List<String> getCollections(@PathVariable String dbName) {
        return mongoService.listCollections(dbName);
    }

    // 4️⃣ Créer une collection
    @PostMapping("/collections/create/{dbName}/{collectionName}")
    public String createCollection(@PathVariable String dbName, @PathVariable String collectionName) {
        return mongoService.createCollection(dbName, collectionName);
    }

    // Supprimer une collection
    @DeleteMapping("/collections/delete/{dbName}/{collectionName}")
    public String deleteCollection(@PathVariable String dbName, @PathVariable String collectionName) {
        return mongoService.deleteCollection(dbName, collectionName);
    }

    // 5️⃣ Ajouter un document
    @PostMapping("/documents/add/{dbName}/{collectionName}")
    public String addDocument(@PathVariable String dbName, @PathVariable String collectionName, @RequestBody Document document) {
        return mongoService.addDocument(dbName, collectionName, document);
    }

    // 6️⃣ Afficher un document
    @GetMapping("/documents/{dbName}/{collectionName}/{key}/{value}")
    public ResponseEntity<Document> getDocument(@PathVariable String dbName, @PathVariable String collectionName, @PathVariable String key, @PathVariable String value) {
        Document document = mongoService.getDocument(dbName, collectionName, key, value);
        return document != null ? ResponseEntity.ok(document) : ResponseEntity.notFound().build();
    }

    // Mettre à jour un document
    @PutMapping("/documents/update/{dbName}/{collectionName}/{key}/{value}")
    public String updateDocument(@PathVariable String dbName, @PathVariable String collectionName, @PathVariable String key, @PathVariable String value, @RequestBody Document update) {
        return mongoService.updateDocument(dbName, collectionName, key, value, update);
    }

    // Supprimer un document
    @DeleteMapping("/documents/delete/{dbName}/{collectionName}/{key}/{value}")
    public String deleteDocument(@PathVariable String dbName, @PathVariable String collectionName, @PathVariable String key, @PathVariable String value) {
        return mongoService.deleteDocument(dbName, collectionName, key, value);
    }
}

