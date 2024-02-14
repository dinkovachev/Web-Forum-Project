package com.telerikacademy.web.sportforumgroup10.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonIgnore
    private int id;
    @NotNull(message = "Can't be empty")
    @Size(min = 4, max = 32, message = "First Name must be between 4 and 32 symbols")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Can't be empty")
    @Size(min = 4, max = 32, message = "Last Name must be between 4 and 32 symbols")
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "Can't be empty")
    @Email
    @Unique
    @Column(name = "email")
    private String email;
    @NotNull(message = "Can't be empty")
    @Column(name = "username")
    private String username;
    @NotNull(message = "Can't be empty")
    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_posts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Post> usersPosts;

    public User() {
    }

    public User(int id, String firstName, String lastName, String email, String username,
                String password, boolean isBlocked, boolean isDeleted, boolean isAdmin) {
//        setId(id);
//        setFirstName(firstName);
//        setLastName(lastName);
//        setEmail(email);
//        setUsername(username);
//        setPassword(password);
//        setBlocked(isBlocked);
//        setBlocked(isDeleted);
//        setAdmin(isAdmin);

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.isDeleted = isDeleted;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Set<Post> getUsersPosts() {
        return usersPosts;
    }

    public void setUsersPosts(Set<Post> usersPosts) {
        this.usersPosts = usersPosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
