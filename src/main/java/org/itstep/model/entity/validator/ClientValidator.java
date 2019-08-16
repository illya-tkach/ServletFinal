package org.itstep.model.entity.validator;

import org.itstep.model.entity.Client;

public class ClientValidator extends EntityValidator<Client> {

    @Override
    public void validate(Client client) {
        if (client.getFirstName().trim().isEmpty()) {
            addError("clientNameError", "Client name cannot be empty");
        }

        if (client.getLastName().trim().isEmpty()) {
            addError("clientLastNameError", "Last name cannot be empty");
        }

        String email = client.getEmail().trim();
        if (email.isEmpty()) {
            addError("emailError", "Email cannot be empty");
        }

        validateWithRegex(email, REGEX_EMAIL, "emailError", "Your email is't correct");

    }
}
