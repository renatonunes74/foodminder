package com.foodminder.FoodMinder.domain.usuario;

public record LoginResponseDTO(String token) {

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}