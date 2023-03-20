package com.example.common.aspect;

import com.auth0.jwk.GuavaCachedJwkProvider;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import com.example.common.dto.CurrentUserInfo;
import com.example.common.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Order(1)
@Aspect
@Component
public class AuthorizeAspect {

    @Autowired
    private CurrentUserInfo currentUserInfo;

//    @Value("${ids4.claims")
//    private Map<String,String> validateClaims;
    @Value("${ids4.jwks.url}")
    private String jwksUrl;
    @Value("${ids4.jwks.issuer}")
    private String issuer;

    @Pointcut("@annotation(com.example.common.annontaion.Authorize)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint point) {
//        long l = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) return;

        HttpServletRequest request = requestAttributes.getRequest();

//        request.setAttribute("start_time", l);
//        String url = request.getRequestURL().toString();
//        String requestMethod = request.getMethod();
//        String ipAddr = getIpAddr(request);
//        Thread currentThread = Thread.currentThread();
//        Object args[] = point.getArgs();
//
//        MethodSignature methodSignature = (MethodSignature) point.getSignature();
//        Method method = methodSignature.getMethod();

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.isBlank(bearerToken) || !validateJwtToken(bearerToken)) {
            throw new UnauthorizedException();
        }
    }

    private boolean validateJwtToken(String originToken) {
        try {
            String token = originToken.split(" ")[1];

            DecodedJWT jwt = JWT.decode(token);
            trustAllHttpsCertificates();
            JwkProvider http = new UrlJwkProvider(new URL(jwksUrl), 1000, null, null);
            JwkProvider provider = new GuavaCachedJwkProvider(http);
            Jwk jwk = provider.get(jwt.getKeyId());
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);

            Verification verification = JWT.require(algorithm).withIssuer(issuer);

//            if (ObjectUtil.isNotNull(validateClaims)) {
//                for (Map.Entry<String, String[]> entry : validateClaims.entrySet()) {
//                    verification.withArrayClaim(entry.getKey(), entry.getValue());
//                }
//            }

            JWTVerifier verifier = verification.build();
            // 必须和认证服务器时钟保持一致,否则认证错误
            DecodedJWT result = verifier.verify(token);

//            currentUserInfo.setUserId(Long.valueOf(result.getSubject()));
//            currentUserInfo.setOrgId(result.getClaim("OrgId").asLong());
//            currentUserInfo.setTenantId(result.getLong("RootOrgId"));
//            currentUserInfo.setRole(result.getClaim("AccountType").asString());
            String preferredUsername = result.getClaim("preferred_username").asString();
            currentUserInfo.setUserId(Long.valueOf(result.getClaim("sub").asString()));
            currentUserInfo.setUsername(preferredUsername);

//            String name = result.getClaim("name").asString();
//            if (StringUtils.isBlank(name)) {
//                name = preferredUsername;
//            }
//            currentUserInfo.setName(name);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

    private static void trustAllHttpsCertificates() {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class miTM implements TrustManager, X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }
    }
}
