package com.warehouse.dto;

import javax.validation.constraints.*;

public class UserDTO {

    @NotNull
    @NotEmpty
    @Size(min = 2,max = 20)
    private String login;

    @NotNull
    @NotEmpty
    @Size(min = 8,max = 50)
    private String password;

    private String confirmPassword;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 2,max = 30)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 2,max = 30)
    private String lastName;

    public UserDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
