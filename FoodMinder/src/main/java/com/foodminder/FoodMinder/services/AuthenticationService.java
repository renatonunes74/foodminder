package com.foodminder.FoodMinder.services;

import com.foodminder.FoodMinder.domain.infra.security.TokenService;
import com.foodminder.FoodMinder.domain.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;
    public ResponseEntity login(AuthenticationDTO data){
        System.out.println("Tentando trocar usuario: " + data.login() + " e " + data.password());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        System.out.println(usernamePassword);
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println("Usuário autenticado com sucesso: " + data.login() + " e " + data.password());
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    public ResponseEntity cadastrar(RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role().toString().toUpperCase());

        this.repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}