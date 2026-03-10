package core.basesyntax;

import core.basesyntax.model.User;
import core.basesyntax.service.RegistrationService;
import core.basesyntax.service.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegistrationServiceTest {
    private static final RegistrationService registrationService = new RegistrationServiceImpl();

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
        User userTest = new User();
        userTest.setAge(10);
        assertThrows(RuntimeException.class, () -> registrationService.register(userTest));
    }

    @Test
    public void userWithSmallLogin_notRegistered() {
        User userTest = new User();
        userTest.setLogin("123");
        assertThrows(RuntimeException.class, () -> registrationService.register(userTest));
    }

    @Test
    public void userWithSmallPassword_notRegistered() {
        User userTest = new User();
        userTest.setPassword("123");
        assertThrows(RuntimeException.class, () -> registrationService.register(userTest));
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
        User userTest = new User();
        userTest.setLogin("user_login");
        assertThrows(RuntimeException.class, () -> registrationService.register(userTest));
    }
}
