package org.itstep.model.entity.validator;

import org.itstep.model.entity.UserAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorTest {
    private UserValidator userValidator;

    @Before
    public void setUp() throws Exception {
        userValidator = new UserValidator();
    }

    @Test
    public void testValidate_valid() {
        UserAccount user = UserAccount.newBuilder()
                .setUserName("username")
                .setPassword("username")
                .build();
        userValidator.validate(user);

        assertFalse(userValidator.hasErrors());
    }

    @Test
    public void testValidate_inValidUserName() {
        UserAccount user = UserAccount.newBuilder()
                .setUserName("")
                .setPassword("password")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }


    @Test
    public void testValidate_invalidPassword() {
        UserAccount user = UserAccount.newBuilder()
                .setUserName("username")
                .setPassword("")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }
}
