package br.com.gpfurlaneto.user.core.util;

import br.com.gpfurlaneto.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

public class JWTUtil {

	private static final String KEY_APP = "key_lista_usuarios_app";

	public static String getToken(UserDto userDto) {
		return Jwts.builder().setSubject(userDto.getLogin())
				.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(KEY_APP))
				.compact();
	}

}
