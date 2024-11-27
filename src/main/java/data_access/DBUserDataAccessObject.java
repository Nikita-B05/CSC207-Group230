package data_access;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.dark_mode.DarkModeUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.settings.SettingsUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        DarkModeUserDataAccessInterface,
        SettingsUserDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String DARK_MODE_KEY = "darkMode";
    private static final String MESSAGE_KEY = "message";

    private final UserFactory userFactory;
    private String currentUsername = null;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public User get(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                throw new RuntimeException("Response body is null");
            }
            final JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String name = userJSONObject.getString(USERNAME_KEY);
                final String password = userJSONObject.getString(PASSWORD_KEY);
                final boolean darkModeEnabled = userJSONObject.getBoolean(DARK_MODE_KEY);
                return userFactory.create(name, password);
            } else {
                throw new RuntimeException(responseBody.optString(MESSAGE_KEY, "Unknown error"));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error fetching user data: " + ex.getMessage(), ex);
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
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);

        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME_KEY, user.getName());
        requestBody.put(PASSWORD_KEY, user.getPassword());
        requestBody.put(DARK_MODE_KEY, user.getDarkMode());

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                throw new RuntimeException("Response body is null");
            }
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) != SUCCESS_CODE) {
                throw new RuntimeException(responseBody.optString(MESSAGE_KEY, "Unknown error"));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error saving user data: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void changePassword(User user) {
        updateUser(user, "PUT");
    }

    @Override
    public void updateUserDarkMode(User user) {
        updateUser(user, "PUT");
    }

    private void updateUser(User user, String method) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);

        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME_KEY, user.getName());
        requestBody.put(PASSWORD_KEY, user.getPassword());
        requestBody.put(DARK_MODE_KEY, user.getDarkMode());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method(method, body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                throw new RuntimeException("Response body is null");
            }
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) != SUCCESS_CODE) {
                throw new RuntimeException(responseBody.optString(MESSAGE_KEY, "Unknown error"));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error updating user data: " + ex.getMessage(), ex);
        }
    }

    @Override
    public boolean existsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                throw new RuntimeException("Response body is null");
            }
            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error checking user existence: " + ex.getMessage(), ex);
        }
    }
}
