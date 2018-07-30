package com.sfu.cmpt470.DAO;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sfu.cmpt470.Util.LoginException;
import com.sfu.cmpt470.Util.StringUtil;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.SessionToken;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;

import static java.sql.Types.TIME;

public class LoginDAO extends BaseDAO{

    public LoginDAO(){
        super();
    }

    public LoginDAO(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        super(connector);
    }

    public SessionToken login(String userName, String hashedPassword) throws LoginException {
        //validate userName and password to the db

        String token;
        try {
            _db.supplyQuery("SELECT password FROM user_user WHERE username = ?");
            _db.setString(userName,1);
            String password = _db.queryOneRecord((rs, rowNum) -> rs.getString("password"));
            if(!Objects.equals(password,hashedPassword)){
                throw new LoginException("password or username is incorrect!");
            }
            Algorithm algorithm = Algorithm.HMAC256("OrderMadeSimple");
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username",userName)
                    .sign(algorithm);
            createToken(userName, token);
        } catch (JWTCreationException | UnsupportedEncodingException exception){
            throw new LoginException("Internal Server Error");
        } catch (SQLException e) {
            throw new LoginException("password or username is incorrect!");
        }

        SessionToken sessionToken = new SessionToken(token);
        sessionToken.setUserName(userName);
        return sessionToken;
    }

    private void createToken(String userName, String tokenString) throws SQLException {
        _db.supplyQuery("INSERT INTO token (token, username, expire_time) VALUES(?,?,?)");
        _db.setString(tokenString, 1);
        _db.setString(userName, 2);
        _db.setTime(OffsetDateTime.now().plusDays(1), 3);
        _db.executeUpdate();
    }

    public void validateToken(String sessionToken) throws JWTVerificationException, UnsupportedEncodingException, SQLException {
        //validate to the db to see if token is a valid one
            Algorithm algorithm = Algorithm.HMAC256("OrderMadeSimple");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(sessionToken);
        String username = jwt.getClaim("username").as(String.class);
        _db.supplyQuery("SELECT restaurant_id FROM user_user WHERE username = ?");
        _db.setString(username,1);
        _db.queryOneRecord((rs, rowNum) -> rs.getLong("restaurant_id"));

        _db.supplyQuery("SELECT expire_time FROM token WHERE username = ? AND token = ? ORDER BY expire_time DESC LIMIT 1");
        _db.setString(username, 1);
        _db.setString(sessionToken, 2);
        OffsetDateTime expireTime = _db.queryOneRecord((rs, rowNum) -> rs.getObject("expire_time", OffsetDateTime.class));
        if(expireTime.isBefore(OffsetDateTime.now())){
            throw new JWTVerificationException("token expired");
        }
    }

    public static void main(String[] args){
        System.out.println(StringUtil.SHA1Hash("password"));
    }
}
