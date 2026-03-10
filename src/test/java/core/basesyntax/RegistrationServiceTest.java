package core.basesyntax;

import core.basesyntax.model.User;
import core.basesyntax.service.RegistrationService;
import core.basesyntax.service.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationServiceTest {
    private static final RegistrationService registrationService = new RegistrationServiceImpl();
    User user_test = new User();
    @BeforeAll
    public static void setUp() {
        User user = new User();
        user.setId(11111111L);
        user.setLogin("user_login");
        user.setPassword("user_password");
        user.setAge(20);
        registrationService.register(user);
    }

    @Test
    public void userWithUnder18_notRegistered() {
        User user_test = new User();
        user_test.setAge(10);
        assertThrows(RuntimeException.class, () -> registrationService.register(user_test));
    }

    @Test
    public void userWithSmallLogin_notRegistered() {
        User user_test = new User();
        user_test.setLogin("123");
        assertThrows(RuntimeException.class, () -> registrationService.register(user_test));
    }
    @Test
    public void userWithSmallPassword_notRegistered() {
        User user_test = new User();
        user_test.setPassword("123");
        assertThrows(RuntimeException.class, () -> registrationService.register(user_test));
    }

    @Test
    public void userWithCorrectData_registered() {
        User newUser = new User();
        newUser.setAge(20);
        newUser.setPassword("securePassword");
        newUser.setLogin("12345678");

        assertEquals(newUser, registrationService.register(newUser));
    }

    @Test
    public void userWithExistedLogin_notRegistered() {
        User user_test = new User();
        user_test.setLogin("user_login");
        assertThrows(RuntimeException.class, () -> registrationService.register(user_test));
    }

}