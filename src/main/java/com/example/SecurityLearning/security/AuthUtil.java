package com.example.SecurityLearning.security;

import com.example.SecurityLearning.entity.User;
import com.example.SecurityLearning.entity.type.AuthProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class AuthUtil {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    // ==========================================================
    // JWT METHODS
    // ==========================================================

    public String generateAccessToken(User user) {

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10 * 10))
                .signWith(getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token,
                              Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenExpired(String token) {

        return extractClaim(token,
                Claims::getExpiration).before(new Date());
    }

    public boolean validateToken(String token,
                                 UserDetails userDetails) {

        String username = getUsernameFromToken(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    // ==========================================================
    // OAUTH2 METHODS
    // ==========================================================

    public AuthProviderType getProviderTypeFromRegistrationId(String registrationId) {

        return switch (registrationId.toLowerCase()) {

            case "google" -> AuthProviderType.GOOGLE;

            case "github" -> AuthProviderType.GITHUB;

            default -> AuthProviderType.EMAIL;
        };
    }

    public String determineProviderIdFromOAuth2User(
            OAuth2User oAuth2User,
            String registrationId
    ) {

        return switch (registrationId.toLowerCase()) {

            case "google" ->
                    oAuth2User.getAttribute("sub");

            case "github" ->
                    String.valueOf(oAuth2User.getAttribute("id"));

            default ->
                    null;
        };
    }

    public String determineUsernameFromOAuth2User(
            OAuth2User oAuth2User,
            String registrationId,
            String providerId
    ) {

        String email = oAuth2User.getAttribute("email");

        if (email != null && !email.isBlank()) {
            return email;
        }

        String login = oAuth2User.getAttribute("login");

        if (login != null && !login.isBlank()) {
            return login;
        }

        return registrationId + "_" + providerId;
    }

}