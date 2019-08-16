package org.itstep.model.entity.validator;

import org.itstep.model.entity.Client;
import org.itstep.model.entity.UserAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClientValidatorTest {
    private ClientValidator clientValidator;

    @Before
    public void setUp() throws Exception {
        clientValidator = new ClientValidator();
    }

    @Test
    public void testValidate_valid() {
        Client client = Client.newBuilder()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("ivanov@gmail.com")
                .build();
        clientValidator.validate(client);

        assertFalse(clientValidator.hasErrors());
    }

    @Test
    public void testValidate_inValidFirstName() {
        Client client = Client.newBuilder()
                .setFirstName("")
                .setLastName("Ivanov")
                .setEmail("ivanov@gmail.com")
                .build();
        clientValidator.validate(client);

        assertTrue(clientValidator.hasErrors());
    }


    @Test
    public void testValidate_invalidLastName() {
        Client client = Client.newBuilder()
                .setFirstName("Ivan")
                .setLastName("")
                .setEmail("ivanov@gmail.com")
                .build();
        clientValidator.validate(client);

        assertTrue(clientValidator.hasErrors());
    }

    @Test
    public void testValidate_emptyEmail() {
        Client client = Client.newBuilder()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("")
                .build();
        clientValidator.validate(client);

        assertTrue(clientValidator.hasErrors());
    }
    @Test
    public void testValidate_invalidEmail() {
        Client client = Client.newBuilder()
                .setFirstName("Ivan")
                .setLastName("")
                .setEmail("ivanov.com")
                .build();
        clientValidator.validate(client);

        assertTrue(clientValidator.hasErrors());
    }
}
