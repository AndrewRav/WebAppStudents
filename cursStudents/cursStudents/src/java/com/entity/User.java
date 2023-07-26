package com.entity;

/**
 *
 * @author Andrew
 */
public class User {
    private int id;
    private String login, password, lasName, firstName, middleName, email, status;
    
    public User(int id, String login, String password, String lasName, String firstName, String middleName, String email, String status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.lasName = lasName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", password=" + password + ", lasName=" + lasName + ", firstName=" + firstName + ", middleName=" + middleName + ", email=" + email + ", status=" + status + '}';
    }
}
