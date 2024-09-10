package br.com.fiap.techecomm.shoppingcart.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Log4j2
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String getSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(Exception e) {
            log.warn(e);
            return null;
        }
    }

    public String getClaimToString(String token, String claim) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return extractClaim(token, claim).asString();
        } catch(Exception e) {
            log.warn(e);
            return null;
        }
    }

    public Long getClaimToLong(String token, String claim) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return extractClaim(token, claim).asLong();
        } catch(Exception e) {
            log.warn(e);
            return null;
        }
    }

    public Claim extractClaim(String token, String claim) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getClaim(claim);
        } catch(Exception e) {
            log.warn(e);
            return null;
        }
    }


}
