package pro.fontoura.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.security}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private Long expitation;
	
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expitation))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null ) {
			String username = claims.getSubject();
			Date expiration = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			if(username != null && expiration != null && now.before(expiration)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	public String getUserName(String token) {
		Claims claims = getClaims(token);
		if (claims != null ) {
			return claims.getSubject();
		}
		return null;
	}
}
