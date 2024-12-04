package use_case.input_name;

import data_access.MongoDBUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputNameInteractorTest {
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
    void inputCharacterNameTest() {
        InputNameOutputBoundary namePresenter = new InputNameOutputBoundary() {
            @Override
            public void presentCharacterNameInput(InputNameOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
                assertEquals("example", outputData.getCharacterName());
            }
        };

        InputNameInputBoundary interactor = new InputNameInteractor(userRepository, namePresenter);
        interactor.inputCharacterName(new InputNameInputData("testing", "example"));
    }
}