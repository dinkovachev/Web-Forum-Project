package com.telerikacademy.web.sportforumgroup10.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.Objects;

public class UserDTO {
    private static final String NAME_CAN_T_BE_EMPTY_MESSAGE = "Name can't be empty";
    private static final String NAME_SHOULD_BE_BETWEEN_4_AND_32_SYMBOLS_MESSAGE = "Name should be between 4 and 32 symbols";
    @NotNull(message = NAME_CAN_T_BE_EMPTY_MESSAGE)
    @Size(min = 4, max = 32, message = NAME_SHOULD_BE_BETWEEN_4_AND_32_SYMBOLS_MESSAGE)
    private String firstName;
    @NotNull(message = NAME_CAN_T_BE_EMPTY_MESSAGE)
    @Size(min = 4, max = 32, message = NAME_SHOULD_BE_BETWEEN_4_AND_32_SYMBOLS_MESSAGE)
    private String lastName;

    //ToDo double check how to use @Unique annotation
    @NotNull(message = NAME_CAN_T_BE_EMPTY_MESSAGE)
    private String email;

    //ToDo double check how to use @Unique annotation
    @NotNull(message = NAME_CAN_T_BE_EMPTY_MESSAGE)
    private String username;
    @NotNull(message = NAME_CAN_T_BE_EMPTY_MESSAGE)
    private String password;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, String email, String username, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setUsername(username);
        setPassword(password);
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.username = username;
//        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(email, userDTO.email) && Objects.equals(username, userDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, username);
    }
}
