package com.zerobase.realestate.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtTokenProvider {

  private String secretKey = "236979CB6F1AD6B6A6184A31E6BE37DB3818CC36871E26235DD67DCFE4041492";

  private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));

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
