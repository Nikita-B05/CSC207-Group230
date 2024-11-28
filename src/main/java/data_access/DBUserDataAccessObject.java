package data_access;

import java.io.IOException;
<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entity.Decision;
import org.json.JSONArray;
=======
import java.util.ArrayList;

import converters.EntityConverterInterface;
import converters.EntityConverter;
import entity.*;
>>>>>>> main
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.homepage.HomepageUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        HomepageUserDataAccessInterface
{
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String INFO = "info";
    private static final String DARK_MODE = "darkMode";
    private static final String CHARACTER_NAME = "characterName";
    private static final String AVATAR = "avatar";
    private static final String HAPPINESS = "happiness";
    private static final String SALARY = "salary";
    private static final String ASSETS = "assets";
    private static final String LIABILITIES = "liabilities";
    private static final String DECISIONS = "decisions";

    private static final String MESSAGE = "message";
    private final UserFactory userFactory;
    private final EntityConverterInterface converter;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        this.converter = new EntityConverter();
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User get(String username) {
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");

                String dbUsername = userJSONObject.has(USERNAME) ? userJSONObject.getString(USERNAME) : username;

                final String password = userJSONObject.has(PASSWORD) ? userJSONObject.getString(PASSWORD) : null;

                if (!userJSONObject.has(INFO) || userJSONObject.isNull(INFO)) {
                    return userFactory.create(dbUsername, password);
                }
                JSONObject infoJSON = userJSONObject.getJSONObject(INFO);

                final boolean isDarkMode = infoJSON.has(DARK_MODE) ? infoJSON.getBoolean(DARK_MODE) : false;

                final String characterName = infoJSON.has(CHARACTER_NAME) ?
                        infoJSON.getString(CHARACTER_NAME) : null;

                Avatar avatar = new Avatar();
                if (infoJSON.has(AVATAR)) {
                    converter.toAvatar(infoJSON.getJSONObject(AVATAR));
                }

                final int happiness = infoJSON.has(HAPPINESS) ? infoJSON.getInt(HAPPINESS) : 0;
                final int salary = infoJSON.has(SALARY) ? infoJSON.getInt(SALARY) : 0;

                Assets assets = new Assets();
                if (infoJSON.has(ASSETS)) {
                    converter.toAssets(infoJSON.getJSONObject(ASSETS));
                }

                Liabilities liabilities = new Liabilities();
                if (infoJSON.has(LIABILITIES)) {
                    converter.toLiabilities(infoJSON.getJSONObject(LIABILITIES));
                }

                ArrayList<Decision> decisions = new ArrayList<>();
                if (infoJSON.has(DECISIONS)) {
                    decisions = converter.toArrayListOfDecision(infoJSON.getJSONArray(DECISIONS));
                }

                return userFactory.create(
                        dbUsername,
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
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        // this isn't implemented for the lab
    }

    @Override
    public boolean existsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();

        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());

        final JSONObject infoBody = new JSONObject();

        infoBody.put(DARK_MODE, user.isDarkMode());
        infoBody.put(CHARACTER_NAME, user.getCharacterName());
        infoBody.put(AVATAR, converter.toJSONObject(user.getAvatar()));
        infoBody.put(HAPPINESS, user.getHappiness());
        infoBody.put(SALARY, user.getSalary());
        infoBody.put(ASSETS, converter.toJSONObject(user.getAssets()));
        infoBody.put(LIABILITIES, converter.toJSONObject(user.getLiabilities()));
        infoBody.put(DECISIONS, converter.toJSONArray(user.getDecisions()));

        requestBody.put(INFO, infoBody);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void changePassword(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

//    @Override
    public void updateUser(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        if (!existsByName(user.getUsername())) {
            throw new RuntimeException(String.format("User %s does not exist", user.getUsername()));
        }

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();

        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());

        final JSONObject infoBody = new JSONObject();

        infoBody.put(DARK_MODE, user.isDarkMode());
        infoBody.put(CHARACTER_NAME, user.getCharacterName());
        infoBody.put(AVATAR, converter.toJSONObject(user.getAvatar()));
        infoBody.put(HAPPINESS, user.getHappiness());
        infoBody.put(SALARY, user.getSalary());
        infoBody.put(ASSETS, converter.toJSONObject(user.getAssets()));
        infoBody.put(LIABILITIES, converter.toJSONObject(user.getLiabilities()));
        infoBody.put(DECISIONS, converter.toJSONArray(user.getDecisions()));

        requestBody.put(INFO, infoBody);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getCurrentUsername() {
        return null;
    }

<<<<<<< HEAD
    public List<Decision> getUserDecisions(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/decisions?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONArray decisionsArray = responseBody.getJSONArray("decisions");
                final List<Decision> decisions = new ArrayList<>();

                for (int i = 0; i < decisionsArray.length(); i++) {
                    final JSONObject decisionObject = decisionsArray.getJSONObject(i);
                    final String timestampString = decisionObject.getString("timestamp");
                    LocalDateTime timestamp = LocalDateTime.parse(timestampString, DateTimeFormatter.ofPattern("MM/dd HH:mm"));
                    final String decisionText = decisionObject.getString("decisionText");
                    final String decisionResponse = decisionObject.getString("decisionResponse");
                    final int happinessImpact = decisionObject.getInt("happinessImpact");
                    final int netWorthImpact = decisionObject.getInt("netWorthImpact");

                    decisions.add(new Decision(timestamp, decisionText, decisionResponse, happinessImpact, netWorthImpact));
                }

                return decisions;
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

}
=======
    public static void main(String[] args) {
        UserFactory userFactory = new CommonUserFactory();
        DBUserDataAccessObject dao = new DBUserDataAccessObject(userFactory);
        User user = userFactory.create(
                "Paul",
                "abc",
                true,
                "Joe Roggens",
                new Avatar(),
                0,
                100000,
                null,
                null,
                new ArrayList<>()
        );
        dao.updateUser(user);
        user = dao.get("Paul");
    }
}
>>>>>>> main
