package com.bridgelabz.userregistrationservice.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtility {

    private final String SECRET = "MilindKumarGupta";
    public String createToken(long id){
        String token;
        token = JWT.create().withClaim("id",id).sign(Algorithm.HMAC256(SECRET));
        return token;
    }
    public int decodeToken(String token){
        int id =0;
        if(token != null){
            id=JWT.require(Algorithm.HMAC256(SECRET))
                    .build().verify(token)
                    .getClaim("id").asInt();
        }return id;
    }

    /*public static final String TOKEN_SECRET ="Milind";
    public String createToken(Long userId) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // String token = JWT.create().withClaim("user_Id", id).sign(algorithm);
        String token = JWT.create().withClaim("user_id",userId).sign(algorithm);
        return token;
    }

    public Long decodeToken(String token){
        //Long user_Id= null;
         return JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token).getClaim("user_id").asLong();
       *//* Verification verification = null;
        verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        JWTVerifier jwtverifier=verification.build();
        DecodedJWT decodedjwt=jwtverifier.verify(token);
        Claim claim=decodedjwt.getClaim("user_Id");
        user_Id=claim.asLong();
        return user_Id;*//*
    }*/
}
