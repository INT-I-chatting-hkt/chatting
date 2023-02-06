package com.example.IntI.security;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private String secretKey="kth";

    private long tokenValidTime=30*60*1000L;
    private final String AUTHORITIES_KEY = "role";


    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(String validateValue) {
        Date now = new Date();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .setSubject(validateValue)
                .setIssuer("debrains")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .compact();
    }
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // 토큰에서 회원 정보 추출
    public String getValidateValue(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveCookieToken(HttpServletRequest request, String key){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key))
                    return cookie.getValue();
            }
        }
        return null;
    }
    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }

}
