package com.zerobase.realestate.security.service;

import com.zerobase.realestate.entity.User;
import com.zerobase.realestate.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Getter
public class JwtTokenProvider {

  private Key key;

  private final UserRepository userRepository;

  public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, UserRepository userRepository) {
    byte[] bytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(bytes);

    this.userRepository = userRepository;
  }

  public static final String USER_KEY = "userKey";

  private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
  private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14; // 14일


  /**
   * Refresh 토큰 생성
   */
  public String createRefreshToken() {

    Date now = new Date();

    return Jwts.builder()
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();

  }

  /**
   * RefreshToken DB 저장(업데이트)
   */
  @Transactional
  public void updateRefreshToken(String id, String refreshToken) {
    Optional<User> userOptional = userRepository.findById(id);
    User user = userOptional.get();
    user.updateRefreshToken(refreshToken);
  }

  /**
   * Access 토큰 생성
   */
  public String createAccessToken(Long userKey) {
    Claims claims = Jwts.claims();
    claims.put(USER_KEY, userKey);

    Date now = new Date();

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

}
