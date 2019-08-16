package org.itstep.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


public class Client {

    private int id;

    private String email;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private Set<Record> records;

    private int balance;

    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void addMoney(int money) {
        this.balance = this.balance + money;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public static Client.Builder newBuilder() {
        return new Client().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Client.Builder setId(int clientId) {
            Client.this.id = clientId;

            return this;
        }

        public Client.Builder setEmail(String clientEmail) {
            Client.this.email = clientEmail;

            return this;
        }

        public Client.Builder setFirstName(String firstName) {
            Client.this.firstName = firstName;

            return this;
        }

        public Client.Builder setLastName(String lastName) {
            Client.this.lastName = lastName;

            return this;
        }

        public Client.Builder setBalance(int balance) {
            Client.this.balance = balance;

            return this;
        }

        public Client.Builder setPhoneNumber(String phoneNumber) {
            Client.this.phoneNumber = phoneNumber;

            return this;
        }

        public Client build() {
            return Client.this;
        }

    }
}
