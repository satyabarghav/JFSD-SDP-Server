package com.klef.jfsd.sdp.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.DTO.UserDetailsResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	@Value("${my.secret}")
    private String secretKey; 


    public String generateToken(UserDetailsResponse userDetailsResponse) {
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("firstName", userDetailsResponse.getFirstName());
	    claims.put("lastName", userDetailsResponse.getLastName());
	    claims.put("email", userDetailsResponse.getEmail());
	    claims.put("role", "ROLE_"+userDetailsResponse.getRole());
	    claims.put("id",userDetailsResponse.getId());
	    claims.put("regNum", userDetailsResponse.getRegNum());
	    return Jwts.builder()
	            .claims() 
	            .add(claims)
	            .subject(userDetailsResponse.getUsername())
	            .issuedAt(new Date(System.currentTimeMillis()))
	            .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30 * 1000))
	            .and().signWith(getKey())
	            .compact();
	}

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        final String userRole = extractRole(token); 
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token) && userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(userRole)));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}