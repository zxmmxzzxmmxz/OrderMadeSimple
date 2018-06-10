package com.sfu.cmpt470.DAO;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sfu.cmpt470.Util.LoginException;
import com.sfu.cmpt470.pojo.SessionToken;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class LoginDAO extends BaseDAO{
    public LoginDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    public SessionToken login(String userName, String hashedPassword) throws LoginException {
        //validate userName and password to the db
        //throw  new LoginException("haha");
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256("orderit");
            token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (JWTCreationException | UnsupportedEncodingException exception){
            throw new LoginException("Internal Server Error");
        }
        return new SessionToken(token);
    }

    public void validateToken(String sessionToken) throws JWTVerificationException, UnsupportedEncodingException {
        //validate to the db to see if token is a valid one
            Algorithm algorithm = Algorithm.HMAC256("orderit");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(sessionToken);
    }
}
