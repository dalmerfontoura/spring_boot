package pro.fontoura.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
}
