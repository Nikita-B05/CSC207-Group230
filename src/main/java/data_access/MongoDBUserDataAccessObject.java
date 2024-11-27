package data_access;

import java.util.Optional;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.dark_mode.DarkModeUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.settings.SettingsUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data, now using MongoDB.
 */
public class MongoDBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        DarkModeUserDataAccessInterface,
        SettingsUserDataAccessInterface {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DARK_MODE = "darkMode";

    private final UserFactory userFactory;
    private final MongoDBConnection mongoDBConnection;
    private String currentUsername = null;

    public MongoDBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        this.mongoDBConnection = new MongoDBConnection();
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
            boolean darkModeEnabled = userDoc.getBoolean(DARK_MODE);
//            boolean darkModeEnabled = userDoc.getBoolean(DARK_MODE, false);
            return userFactory.create(name, password, darkModeEnabled);
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

    @Override
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
                .append(DARK_MODE, user.isDarkMode());

        usersCollection.insertOne(userDoc);
    }

    @Override
    public void changePassword(User user) {
        updateUser(user, PASSWORD, user.getPassword());
    }

    @Override
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
}
