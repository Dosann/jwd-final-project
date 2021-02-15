package com.epam.jwd.training.entity;

public class User implements BaseEntity {

    private int id;
    private String login;
    private String password;
    private String name;
    private String email;
    private UserRole role;

    public User(int id, String login, String password, String name, String email, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "login=" + login +
                ", password=" + password +
                ", name=" + name +
                ", email=" + email +
                ", role=" + role +
                '}';
    }
}
