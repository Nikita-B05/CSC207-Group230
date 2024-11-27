package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClients;
import data_access.config.ConfigLoader;
import org.bson.Document;

public class MongoDBConnection {

    private final com.mongodb.client.MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    public MongoDBConnection() {
        String mongoUri = ConfigLoader.getProperty("mongo.uri");
        String databaseName = ConfigLoader.getProperty("mongo.database");
        String collectionName = ConfigLoader.getProperty("mongo.collection");

        this.mongoClient = MongoClients.create(mongoUri);
        this.collection = mongoClient.getDatabase(databaseName).getCollection(collectionName);
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void close() {
        mongoClient.close();
    }
}
