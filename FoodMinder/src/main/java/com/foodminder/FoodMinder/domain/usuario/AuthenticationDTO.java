package com.foodminder.FoodMinder.domain.usuario;

public record AuthenticationDTO(String login, String password) {
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}