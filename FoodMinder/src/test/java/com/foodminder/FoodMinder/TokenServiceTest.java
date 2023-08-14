package com.foodminder.FoodMinder;

import com.foodminder.FoodMinder.domain.infra.security.TokenService;
import com.foodminder.FoodMinder.domain.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Value("${api.security.token.secret}")
    private String secret;

    @Test
    public void testGenerateToken_Success() {
        Usuario user = new Usuario("username", "password", "ROLE_USER");
        String token = tokenService.generateToken(user);

        assertNotNull(token);
    }

    @Test
    public void testValidateToken_ValidToken() {
        Usuario user = new Usuario("username", "password", "ROLE_USER");
        String token = tokenService.generateToken(user);

        String validatedUsername = tokenService.validateToken(token);

        assertEquals("username", validatedUsername);
    }

    @Test
    public void testValidateToken_InvalidToken() {
        String invalidToken = "invalidToken";

        String validatedUsername = tokenService.validateToken(invalidToken);

        assertEquals("", validatedUsername);
    }
}
