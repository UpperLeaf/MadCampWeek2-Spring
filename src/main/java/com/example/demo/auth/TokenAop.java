package com.example.demo.auth;

import com.example.demo.exception.UnAuthorizedException;

import com.example.demo.user.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Component
@Aspect
public class TokenAop {

    private final RestTemplate restTemplate;
    private final AccountService accountService;

    @Value("${google.client.secret.id}")
    private String clientId;

    @Around("execution(* *(.., @com.example.demo.auth.TokenLogin (*), ..))")
    public Object checkToken(ProceedingJoinPoint joinPoint) throws Throwable {
        CodeSignature codeSignature = (CodeSignature)joinPoint.getSignature();
        String[] params = codeSignature.getParameterNames();
        for(int i = 0; i < params.length; i++){
            if(params[i].equals("user")){
                HttpServletRequest request =
                        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

                String accessToken = request.getHeader("Authorization");
                try {
                    ResponseEntity<AuthUser> responseEntity = restTemplate
                            .getForEntity("https://oauth2.googleapis.com/tokeninfo?id_token=" + accessToken, AuthUser.class);

                    AuthUser authUser = responseEntity.getBody();

                    UserDetails userDetails;
                    try {
                        userDetails = accountService.loadUserByUsername(authUser.getEmail());
                    }catch (UsernameNotFoundException e){
                        userDetails = accountService.createAccount(authUser);
                    }
                    SecurityContextHolder.getContext().setAuthentication(new UsernameAuthentication(userDetails, true));
                    return joinPoint.proceed(new Object[]{authUser});
                }catch (HttpClientErrorException ex){
                    log.info("Token Invalid");
                    throw new UnAuthorizedException();
                }
            }
        }
        throw new UnAuthorizedException();
    }
}
