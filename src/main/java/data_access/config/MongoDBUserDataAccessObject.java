package data_access.config;

import java.util.ArrayList;
import java.util.Optional;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import converters.EntityConverter;
import converters.EntityConverterInterface;
import data_access.MongoDBConnection;
import entity.*;
import org.bson.Document;

import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data, now using MongoDB.
 */
public class MongoDBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DARK_MODE = "darkMode";
    private static final String CHARACTER_NAME = "characterName";
    private static final String AVATAR = "avatar";
    private static final String HAPPINESS = "happiness";
    private static final String SALARY = "salary";
    private static final String ASSETS = "assets";
    private static final String LIABILITIES = "liabilities";
    private static final String DECISIONS = "decisions";

    private final UserFactory userFactory;
    private final MongoDBConnection mongoDBConnection;
    private final EntityConverterInterface converter;
    private String currentUsername = null;

    public MongoDBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        this.mongoDBConnection = new MongoDBConnection();
        this.converter = new EntityConverter();
    }

    @Override
    public User get(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        MongoCollection<Document> usersCollection = mongoDBConnection.getCollection();

        Document userDoc = usersCollection.find(Filters.eq(USERNAME, username)).first();

        if (userDoc != null) {
            String name = userDoc.getString(USERNAME);
            String password = userDoc.getString(PASSWORD);
            boolean isDarkMode = userDoc.getBoolean(DARK_MODE, false);
            String characterName = userDoc.getString(CHARACTER_NAME);
            Avatar avatar = converter.toAvatar(userDoc.getString(AVATAR));
            int happiness = userDoc.getInteger(HAPPINESS);
            int salary = userDoc.getInteger(SALARY);
            Assets assets = converter.toAssets(userDoc.getString(ASSETS));
            Liabilities liabilities = converter.toLiabilities(userDoc.getString(LIABILITIES));
            ArrayList<Decision> decisions = converter.toArrayListOfDecision(userDoc.getString(DECISIONS));

            return userFactory.create(
                    name,
                    password,
                    isDarkMode,
                    characterName,
                    avatar,
                    happiness,
                    salary,
                    assets,
                    liabilities,
                    decisions
            );
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        if (name != null && name.isEmpty()) {
            throw new IllegalArgumentException("Current username cannot be an empty string");
        }
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        if (currentUsername == null) {
            throw new IllegalStateException("No current user is set");
        }
        return currentUsername;
    }

//    @Override
    public User getCurrentUser() {
        if (currentUsername == null) {
            throw new IllegalStateException("No current user is logged in");
        }
        return get(currentUsername);
    }

    @Override
    public void save(User user) {
        MongoCollection<Document> usersCollection = mongoDBConnection.getCollection();

        Document userDoc = new Document()
                .append(USERNAME, user.getUsername())
                .append(PASSWORD, user.getPassword())
                .append(DARK_MODE, user.isDarkMode())
                .append(CHARACTER_NAME, user.getCharacterName())
                .append(AVATAR, converter.serialize(user.getAvatar()))
                .append(HAPPINESS, user.getHappiness())
                .append(SALARY, user.getSalary())
                .append(ASSETS, converter.serialize(user.getAssets()))
                .append(LIABILITIES, converter.serialize(user.getLiabilities()))
                .append(DECISIONS, converter.serialize(user.getDecisions()));

        usersCollection.insertOne(userDoc);
    }

    @Override
    public void changePassword(User user) {
        updateUser(user, PASSWORD, user.getPassword());
    }

//    @Override
    public void updateUserDarkMode(User user) {
        updateUser(user, DARK_MODE, user.isDarkMode());
    }

    private void updateUser(User user, String key, Object value) {
        MongoCollection<Document> usersCollection = mongoDBConnection.getCollection();

        usersCollection.updateOne(
                Filters.eq(USERNAME, user.getUsername()),
                Updates.set(key, value)
        );
    }

    @Override
    public boolean existsByName(String username) {
        MongoCollection<Document> usersCollection = mongoDBConnection.getCollection();

        Optional<Document> userDoc = Optional.ofNullable(usersCollection.find(Filters.eq(USERNAME, username)).first());

        return userDoc.isPresent();
    }

    private void deleteUser(String user) {
        MongoCollection<Document> usersCollection = mongoDBConnection.getCollection();
        usersCollection.deleteMany(Filters.eq(USERNAME, user));
    }

    public static void main(String[] args) {
        User user = new CommonUser("Test", "1234");
        MongoDBUserDataAccessObject dao = new MongoDBUserDataAccessObject(new CommonUserFactory());
        dao.save(user);
        System.out.println(dao.get(user.getUsername()).getSalary());
        dao.deleteUser(user.getUsername());
    }
}