package use_case.dark_mode;

import data_access.MongoDBUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DarkModeInteractorTest {
    private static MongoDBUserDataAccessObject userRepository;

    @BeforeAll
    public static void setUp() {
        User user = new CommonUser("testing", "password");
        userRepository = new MongoDBUserDataAccessObject(new CommonUserFactory());
        userRepository.setCurrentUsername("testing");
        userRepository.save(user);
    }

    @AfterAll
    public static void tearDown() {
        userRepository.deleteUser("testing");
    }

    @Test
    void toggleDarkModeTest() {
        DarkModeOutputBoundary darkModePresenter = new DarkModeOutputBoundary() {
            @Override
            public void updateUIMode(DarkModeOutputData outputData) {
                assertEquals(outputData.isDarkMode(), true);
            }
        };

        DarkModeInputBoundary interactor = new DarkModeInteractor(darkModePresenter, userRepository);
        interactor.toggleDarkMode(new DarkModeInputData(true));
    }
}
