package com.telerikacademy.web.sportforumgroup10.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "is_admin")
    private boolean isAdmin;

    public User() {
    }

    public User(int id, String firstName, String lastName, String email, String username,
                String password, boolean isBlocked, boolean isDeleted, boolean isAdmin) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setBlocked(isBlocked);
        setBlocked(isDeleted);
        setAdmin(isAdmin);
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.isBlocked = isBlocked;
//        this.isDeleted = isDeleted;
//        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    private void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    private void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    private void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username);
    }
}
