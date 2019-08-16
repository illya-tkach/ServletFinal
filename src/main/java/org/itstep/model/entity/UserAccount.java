package org.itstep.model.entity;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {

    private int id;

    private String userName;

    private String password;

    private List<String> roles;

    public UserAccount() {

    }

    public UserAccount(String userName, String password, String... roles) {
        this.userName = userName;
        this.password = password;

        this.roles = new ArrayList<String>();
        if (roles != null) {
            for (String r : roles) {
                this.roles.add(r);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static Builder newBuilder() {
        return new UserAccount().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setUserId(int userId) {
            UserAccount.this.id = userId;

            return this;
        }

        public Builder setUserName(String userName) {
            UserAccount.this.userName = userName;

            return this;
        }

        public Builder setPassword(String password) {
            UserAccount.this.password = password;

            return this;
        }

        public Builder setRoles(List<String> roles) {
            UserAccount.this.roles = roles;

            return this;
        }

        public UserAccount build() {
            return UserAccount.this;
        }

    }
}