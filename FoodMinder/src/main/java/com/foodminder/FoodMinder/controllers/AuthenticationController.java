package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.usuario.AuthenticationDTO;
import com.foodminder.FoodMinder.domain.usuario.RegisterDTO;
import com.foodminder.FoodMinder.domain.usuario.Usuario;
import com.foodminder.FoodMinder.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/cadastrar")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if(this.usuarioRepository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        var newUsuario = new Usuario(registerDTO.login(), encryptedPassword, registerDTO.role());
        this.usuarioRepository.save(newUsuario);
        return ResponseEntity.ok().build();
    }
}
