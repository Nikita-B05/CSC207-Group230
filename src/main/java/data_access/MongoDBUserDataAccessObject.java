package data_access;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import converters.EntityConverter;
import converters.EntityConverterInterface;
import entity.*;
import org.bson.Document;

import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.choose_asset.ChooseAssetDataAccessInterface;
import use_case.decision_log.DecisionLogUserDataAccessInterface;
import use_case.game_decision.GameDecisionUserDataAccessInterface;
import use_case.homepage.HomepageUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.manage_home.ManageHomeDataAccessInterface;
import use_case.manage_stock.ManageStockDataAccessInterface;
import use_case.settings.SettingsUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.choose_avatar.ChooseAvatarUserDataAccessInterface;
import use_case.input_name.InputNameUserDataAccessInterface;

/**
 * The DAO for user data, now using MongoDB.
 */
public class MongoDBUserDataAccessObject implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        ChooseAvatarUserDataAccessInterface,
        InputNameUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        HomepageUserDataAccessInterface,
        SettingsUserDataAccessInterface,
        DecisionLogUserDataAccessInterface {
        ChooseAssetDataAccessInterface,
        ManageHomeDataAccessInterface,
        ManageStockDataAccessInterface,
        GameDecisionUserDataAccessInterface {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String AGE = "age";
    private static final String DARK_MODE = "darkMode";
    private static final String CHARACTER_NAME = "characterName";
    private static final String AVATAR = "avatar";
    private static final String HAPPINESS = "happiness";
    private static final String SALARY = "salary";
    private static final String ASSETS = "assets";
    private static final String LIABILITIES = "liabilities";
    private static final String DECISIONS = "decisions";
    private static final Object QUESTION_BANK = "questionBank";

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
    public List<Decision> getDecisions(String username) {
        return List.of();
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
            int age = userDoc.getInteger(AGE);
            boolean isDarkMode = userDoc.getBoolean(DARK_MODE, false);
            String characterName = userDoc.getString(CHARACTER_NAME);
            Avatar avatar = converter.toAvatar(userDoc.getString(AVATAR));
            int happiness = userDoc.getInteger(HAPPINESS);
            double salary = userDoc.getDouble(SALARY);
            Assets assets = converter.toAssets(userDoc.getString(ASSETS));
            Liabilities liabilities = converter.toLiabilities(userDoc.getString(LIABILITIES));
            ArrayList<Decision> decisions = converter.toArrayListOfDecision(userDoc.getString(DECISIONS));

            return userFactory.create(
                    name,
                    password,
                    age,
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
    public void updateUserDarkMode(boolean isDarkMode) {
        User user = getCurrentUser();
        updateUser(user, DARK_MODE, isDarkMode);
    }

    @Override
    public void updateCharacterName(String characterName) {
        User user = getCurrentUser();
        updateUser(user, CHARACTER_NAME, characterName);
    }

    @Override
    public void updateAvatar(Avatar avatar) {
        User user = getCurrentUser();
        updateUser(user, AVATAR, avatar);
    }

    @Override
    public void updateUserCash(double newCash) {
        User user = getCurrentUser();
        Assets assets = user.getAssets() == null ? new Assets() : user.getAssets();
        Assets newAssets = new Assets(
                assets.getHome(),
                assets.getStocks(),
                newCash,
                assets.getCar()
        );
        updateUser(user, ASSETS, newAssets);
    }

    @Override
    public void updateUserHome(double newHome) {
        User user = getCurrentUser();
        Assets assets = user.getAssets() == null ? new Assets() : user.getAssets();
        Assets newAssets = new Assets(
                newHome,
                assets.getStocks(),
                assets.getCash(),
                assets.getCar()
        );
        updateUser(user, ASSETS, newAssets);
    }

    @Override
    public void updateAssets(Assets assets) {
        User user = getCurrentUser();
        updateUser(user, ASSETS, assets);
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
    public void incrementAge() {
        User user = getCurrentUser();
        updateUser(user, AGE, user.getAge() + 1);
    }

    @Override
    public void updateDecision(User user) {
        updateUser(user, DECISIONS, user.getDecisions());
    }

    @Override
    public void updateAssets(User user) {
        updateUser(user, ASSETS, user.getAssets());
    }

    @Override
    public void updateHappiness(User user) {
        updateUser(user, HAPPINESS, user.getHappiness());
    }

    @Override
    public void updateSalary(User user) {
        updateUser(user, SALARY, user.getSalary());
    }

    @Override
    public void addSalary() {
        User user = getCurrentUser();
        Assets assets = user.getAssets();
        Assets newAssets = new Assets(
                assets.getHome(),
                assets.getStocks(),
                assets.getCash() + user.getSalary(),
                assets.getCar()
        );
        updateUser(user, ASSETS, newAssets);
    }

    @Override
    public void appreciateHome() {
        User user = getCurrentUser();
        Assets assets = user.getAssets();
        Assets newAssets = new Assets(
                assets.getHome() * 1.05,
                assets.getStocks(),
                assets.getCash(),
                assets.getCar()
        );
        updateUser(user, ASSETS, newAssets);
    }

    @Override
    public void save(User user) {
        MongoCollection<Document> usersCollection = mongoDBConnection.getCollection();

        Document userDoc = new Document()
                .append(USERNAME, user.getUsername())
                .append(PASSWORD, user.getPassword())
                .append(AGE, user.getAge())
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

        if (Objects.equals(key, AVATAR)) {
            if (!(value instanceof Avatar)) {
                throw new IllegalArgumentException("Class of <value> must be equal to <key>.");
            }
            usersCollection.updateOne(
                    Filters.eq(USERNAME, user.getUsername()),
                    Updates.set(key, converter.serialize((Avatar) value))
            );
        }
        else if (Objects.equals(key, ASSETS)) {
            if (!(value instanceof Assets)) {
                throw new IllegalArgumentException("Class of <value> must be equal to <key>.");
            }
            usersCollection.updateOne(
                    Filters.eq(USERNAME, user.getUsername()),
                    Updates.set(key, converter.serialize((Assets) value))
            );
        }
        else if (Objects.equals(key, LIABILITIES)) {
            if (!(value instanceof Liabilities)) {
                throw new IllegalArgumentException("Class of <value> must be equal to <key>.");
            }
            usersCollection.updateOne(
                    Filters.eq(USERNAME, user.getUsername()),
                    Updates.set(key, converter.serialize((Liabilities) value))
            );
        }
        else if (Objects.equals(key, DECISIONS)) {
            if (!(value instanceof ArrayList)) {
                throw new IllegalArgumentException("Class of <value> must be equal to <key>.");
            }
            for (Object o : (ArrayList) value) {
                if (!(o instanceof Decision)) {
                    throw new IllegalArgumentException("Value is not an ArrayList of Decision.");
                }
            }
            usersCollection.updateOne(
                    Filters.eq(USERNAME, user.getUsername()),
                    Updates.set(key, converter.serialize((ArrayList<Decision>) value))
            );
        } else {
            usersCollection.updateOne(
                    Filters.eq(USERNAME, user.getUsername()),
                    Updates.set(key, value)
            );
        }
    }

    @Override
    public boolean existsByName(String username) {
        MongoCollection<Document> usersCollection = mongoDBConnection.getCollection();

        Optional<Document> userDoc = Optional.ofNullable(usersCollection.find(Filters.eq(USERNAME, username)).first());

        return userDoc.isPresent();
    }

    public void deleteUser(String user) {
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