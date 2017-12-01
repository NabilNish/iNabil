package com.inabil.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NABIL on 29-11-2017.
 */

public class User extends RealmObject {

    @PrimaryKey
    private String email;

    private String userType;
    private String firstName;
    private String lastName;
    private String mobile;
    private String password;

    public User() {
    }

    public User(String email, String userType, String firstName, String lastName, String mobile, String password) {
        this.email = email;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
