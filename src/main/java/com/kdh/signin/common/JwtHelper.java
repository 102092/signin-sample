package com.kdh.signin.common;

import com.kdh.signin.auth.domain.*;
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
//        claims.put("e", user.getEmail().getUniqueValue());
//        claims.put("nn", user.getNickName().getValue());
//        claims.put("p", user.getPassword().getValue());
//        claims.put("n", user.getName().getValue());
//        claims.put("ep", user.getPhone().getUniqueValue());

        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    }

    public static User decode(String encoded) {
        try {
            Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(encoded).getBody();
            User.UserId i = new User.UserId(body.get("i", Long.class));
//            Email e = new Email(body.get("e", String.class));
//            Password p = new Password(body.get("p", String.class));
//            NickName nn = new NickName(body.get("nn", String.class));
//            Name n = new Name(body.get("n", String.class));
//            Phone ep = Phone.of(body.get("ep", String.class));

            return User.builder()
                .id(i)
//                .email(e)
//                .phone(ep)
//                .password(p)
//                .name(n)
//                .nickName(nn)
                .build();

        } catch (ExpiredJwtException eje) {
            throw new BadRequestException("This jwt is expired");
        } catch (Exception e) {
            throw new BadRequestException("Decode jwt failed");
        }
    }
}
