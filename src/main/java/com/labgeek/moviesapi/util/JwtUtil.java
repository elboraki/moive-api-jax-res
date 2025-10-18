package com.labgeek.moviesapi.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	private static final String MY_SECRET_KEY = "4zgw2mnr";
	private static final long MY_EXPIRATION_TIME = 1000 * 60 * 60;

	public static final String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + MY_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, MY_SECRET_KEY).compact();
	}

	public static final boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(MY_SECRET_KEY).parseClaimsJws(MY_SECRET_KEY);
			return true;
		} catch (Exception e) {

			return false;
		}
	}
	
	public static final String extractUserName(String token) {
		Claims claims=Jwts.parser().setSigningKey(MY_SECRET_KEY).parseClaimsJws(token).getBody();
		return claims.getSubject();
		
	}
}
