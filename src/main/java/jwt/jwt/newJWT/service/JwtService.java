package jwt.jwt.newJWT.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService {
    private static final String SECRET = "01bf77d73a414646b3cf8735bab3dac13bc1310998725f2212ec837de6b661b0";
    public String generateToken(UserDetails user){
        log.info("generate token");
        return Jwts.builder()
                .setSubject(user.getUsername())
                //adding claims to the token
                .claim("authorities", populateAuthorities(user.getAuthorities()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();
        for(GrantedAuthority authority : authorities) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        log.info("Extract claims");
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        log.info("Extract username");
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        log.info("Extract claim");
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
}
