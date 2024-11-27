package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClients;
import org.bson.Document;

/**
 * MongoDBConnection handles the connection to MongoDB and provides access to the users collection.
 */
public class MongoDBConnection {

    private static final String DATABASE_NAME = "mydatabase";
    private static final String COLLECTION_NAME = "users";

    private com.mongodb.client.MongoClient mongoClient;
    private MongoCollection<Document> collection;

    public MongoDBConnection() {
        String mongoUri = "mongodb+srv://csc207:csc207@userdatabase.k8oh9.mongodb.net/?retryWrites=true&w=majority&appName=UserDatabase";
        this.mongoClient = MongoClients.create(mongoUri);
        this.collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void close() {
        mongoClient.close();
    }
}
