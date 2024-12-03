package use_case.choose_avatar;

import data_access.MongoDBUserDataAccessObject;
import entity.Avatar;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChooseAvatarInteractorTest {
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
    void selectAvatarTest() {
        ChooseAvatarOutputBoundary avatarPresenter = new ChooseAvatarOutputBoundary() {

            @Override
            public void presentAvatarSelection(ChooseAvatarOutputData outputData) {
                assertEquals("testing", outputData.getUsername());
            }
        };

        ChooseAvatarInputBoundary interactor = new ChooseAvatarInteractor(
                userRepository,
                avatarPresenter
        );
        ChooseAvatarInputData inputData = new ChooseAvatarInputData("testing", new Avatar());
        interactor.selectAvatar(inputData);
    }
}