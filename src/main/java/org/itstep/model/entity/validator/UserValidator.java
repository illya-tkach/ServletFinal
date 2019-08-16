package org.itstep.model.entity.validator;

import org.itstep.model.entity.UserAccount;

public class UserValidator extends EntityValidator<UserAccount> {

    @Override
    public void validate(UserAccount userAccount) {
        if (userAccount.getUserName().trim().isEmpty()) {
            addError("userNameError", "Username cannot be empty");
        }

        if (userAccount.getPassword().trim().isEmpty()) {
            addError("passwordError", "Password cannot be empty");
        }
    }
}
