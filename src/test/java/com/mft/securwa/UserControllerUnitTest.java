package com.mft.securwa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.mft.securwa.controllers.UserController;
import com.mft.securwa.entities.User;
import com.mft.securwa.repositories.UserRepository;

public class UserControllerUnitTest {

    private static UserController userController;

    @BeforeClass
    public static void setUp() {
        UserRepository mockedUserRepository = mock(UserRepository.class);
        userController = new UserController(mockedUserRepository);
    }

    @Test
    public void whenCalledshowSignUpForm_thenCorrect() {
        User user = new User("John", "john@domain.com");

        assertThat(userController.showSignUpForm(user)).isEqualTo("add-user");
    }
}
