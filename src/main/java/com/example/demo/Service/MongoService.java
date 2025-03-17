package com.example.demo.Service;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoService {
    private final MongoClient mongoClient;

    public MongoService() {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
    }

    public List<String> listDatabases() {
        List<String> databases = new ArrayList<>();
        mongoClient.listDatabaseNames().forEach(databases::add);
        return databases;
    }
    // Créer une base de données (MongoDB crée automatiquement la BD lors de l'ajout d'une collection)
    public String createDatabase(String dbName) {
        mongoClient.getDatabase(dbName).createCollection("temp");
        return "Database " + dbName + " created!";
    }

    // Supprimer une base de données
    public String deleteDatabase(String dbName) {
        mongoClient.getDatabase(dbName).drop();
        return "Database " + dbName + " deleted!";
    }

    // Afficher toutes les collections d'une BD
    public List<String> listCollections(String dbName) {
        List<String> collections = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase(dbName);
        database.listCollectionNames().forEach(collections::add);
        return collections;
    }

    // Créer une collection
    public String createCollection(String dbName, String collectionName) {
        mongoClient.getDatabase(dbName).createCollection(collectionName);
        return "Collection " + collectionName + " created!";
    }

    // Supprimer une collection
    public String deleteCollection(String dbName, String collectionName) {
        mongoClient.getDatabase(dbName).getCollection(collectionName).drop();
        return "Collection " + collectionName + " deleted!";
    }

    // Ajouter un document
    public String addDocument(String dbName, String collectionName, Document document) {
        mongoClient.getDatabase(dbName).getCollection(collectionName).insertOne(document);
        return "Document inserted!";
    }

    // Afficher un document
    public Document getDocument(String dbName, String collectionName, String key, String value) {
        return mongoClient.getDatabase(dbName).getCollection(collectionName)
                .find(new Document(key, value)).first();
    }

    // Mettre à jour un document
    public String updateDocument(String dbName, String collectionName, String key, String value, Document update) {
        mongoClient.getDatabase(dbName).getCollection(collectionName)
                .updateOne(new Document(key, value), new Document("$set", update));
        return "Document updated!";
    }

    // Supprimer un document
    public String deleteDocument(String dbName, String collectionName, String key, String value) {
        mongoClient.getDatabase(dbName).getCollection(collectionName)
                .deleteOne(new Document(key, value));
        return "Document deleted!";
    }
}

