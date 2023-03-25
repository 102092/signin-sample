package com.kdh.signin.common;

import com.kdh.signin.auth.domain.*;
import com.kdh.signin.common.error.BadRequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author han
 */

public class JwtHelper {

    private static final String SECRET = "secret1";

    public static String encode(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("i", user.getId().getId());

        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    }

    public static User decode(String encoded) {
        try {
            Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(encoded).getBody();
            User.UserId i = new User.UserId(body.get("i", Long.class));

            return User.builder()
                .id(i)
                .build();

        } catch (ExpiredJwtException eje) {
            throw new BadRequestException("This jwt is expired");
        } catch (Exception e) {
            throw new BadRequestException("Decode jwt failed");
        }
    }
}
