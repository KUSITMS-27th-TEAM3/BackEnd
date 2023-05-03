package com.kusitms.samsion.common.security.jwt;

import static com.kusitms.samsion.common.consts.ApplicationConst.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.kusitms.samsion.common.exception.Error;
import com.kusitms.samsion.common.security.exception.ExpiredTokenException;
import com.kusitms.samsion.common.security.exception.InvalidTokenException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO : 예외처리에서 사용자 정보를 담어서 로그를 남겨야함, 토큰에 저장할 정보 email로 할지 정하기, refreshToken을 재발급하는 과정을 filter사용할지 아니면 컨트롤러에서 처리할지 정하기
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

	private final JwtProperties jwtProperties;
	private final RedisTemplate<String, String> redisTemplate;

	private Key getSecretKey() {
		return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
	}

	private String buildAccessToken(String email, Date now) {
		return buildToken(now)
			.setExpiration(new Date(now.getTime() + jwtProperties.getAccessTokenPeriod()))
			.setSubject(email)
			.claim(TOKEN_TYPE, ACCESS_TOKEN)
			.compact();
	}

	private String buildRefreshToken(String email, Date now) {
		final String refreshToken = buildToken(now)
			.setExpiration(new Date(now.getTime() + jwtProperties.getRefreshTokenPeriod()))
			.setSubject(email)
			.claim(TOKEN_TYPE, REFRESH_TOKEN)
			.compact();
		storeRefreshToken(email, refreshToken);
		return refreshToken;
	}

	private void storeRefreshToken(String email, String refreshToken) {
		redisTemplate.opsForValue().set(
			email,
			refreshToken,
			jwtProperties.getRefreshTokenPeriod(),
			TimeUnit.MILLISECONDS);
	}

	private String getRefreshToken(String email) {
		return redisTemplate.opsForValue().get(email);
	}

	private JwtBuilder buildToken(Date now) {
		final Key key = getSecretKey();
		return Jwts.builder()
			.setIssuedAt(now)
			.signWith(key);
	}

	public String generateAccessToken(String email) {
		final Date now = new Date();
		return buildAccessToken(email, now);
	}

	public String generateRefreshToken(String email) {
		final Date now = new Date();
		return buildRefreshToken(email, now);
	}

	/**
	 * TODO : 예외 처리 관련해서 좀 더 고민해보기
	 * @throws InvalidTokenException : 유효하지 않은 토큰
	 * @throws ExpiredTokenException : 만료된 토큰
	 * @throws IllegalArgumentException : 토큰이 null일 경우
	 */
	public void validateToken(String token) {
		JwtParser jwtParser = Jwts.parserBuilder()
			.setSigningKey(getSecretKey())
			.build();
		try {
			jwtParser.parse(token);
		} catch (MalformedJwtException | SignatureException | IllegalArgumentException e){
			throw new InvalidTokenException(Error.INVALID_TOKEN);
		} catch (ExpiredJwtException e){
			throw new ExpiredTokenException(Error.EXPIRED_TOKEN);
		}
	}

	public String extractEmail(String token){
		return Jwts.parserBuilder()
			.setSigningKey(getSecretKey())
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	public String reIssue(String refreshToken){
		String email = validateRefreshToken(refreshToken);
		return generateAccessToken(email);
	}

	public String validateRefreshToken(String refreshToken) {
		validateToken(refreshToken);
		final String email = extractEmail(refreshToken);
		final String storedRefreshToken = getRefreshToken(email);
		if(!storedRefreshToken.equals(refreshToken)){
			throw new InvalidTokenException(Error.INVALID_TOKEN);
		}
		return email;
	}


}
