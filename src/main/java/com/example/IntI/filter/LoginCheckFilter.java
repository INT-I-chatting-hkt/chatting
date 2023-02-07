package com.example.IntI.filter;

import com.example.IntI.security.JwtTokenProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
public class LoginCheckFilter implements Filter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String[] whiteURI={"/", "/join", "/login",
            "/logout","/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
    throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try {
            log.info("TRY AUTHENTICATION {}",requestURI);
            if(isLoginCheckPath(requestURI)){
                String token = jwtTokenProvider.resolveCookieToken(httpServletRequest,"inti-token");
                log.info("AUTHENTICATION TOKEN {}", token);
                if (jwtTokenProvider.validateToken(token)&&token!=null){
                    Cookie myCookie = new Cookie("inti-token", token);
                    myCookie.setMaxAge(300);
                    myCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
                    httpServletResponse.addCookie(myCookie);
                }else {
                    log.info("LOGIN FAILURE {}",token);
                    httpServletResponse.sendRedirect("/login");
                    return;
                }
            }
            filterChain.doFilter(request,response);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("END AUTHENTICATION FILTER {}",requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whiteURI,requestURI);
    }
}
