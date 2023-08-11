package com.foodminder.FoodMinder.domain.usuario;
public record RegisterDTO(String login, String password, UsuarioRole role) {
}