package bilgenkaanremzi.CapstoneProjectSteamClone.security;

import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import bilgenkaanremzi.CapstoneProjectSteamClone.entities.User;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {

@Value("${JWT_SECRET}")
    private String secret;

public String createToken(User user) {
    return Jwts.builder().setSubject(String.valueOf(user.getId())).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*7)).signWith((Keys.hmacShaKeyFor(secret.getBytes()))).compact();
}
public void verifyToken(String token) {
    try {
        Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
    } catch (Exception ex) {
        throw new UnauthorizedException("The Token is Not Valid, please re-Login");
    }}

    public String extractIdFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token).getBody().getSubject();
    }
}
