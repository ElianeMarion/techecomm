package br.com.fiap.techecomm.products.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    private static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenLocated = getToken(request);
        tokenLocated.ifPresent(token -> {
            String login = tokenService.getSubject(token);
            String roles = tokenService.getClaimToString(token, "roles");
            Long userId = tokenService.getClaimToLong(token, "userId");
            UserAuthDetails userAuth = new UserAuthDetails(userId, login, roles);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userAuth, null, userAuth.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        });
        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(header -> header.startsWith(BEARER))
                .map(header -> header.replace(BEARER, ""));
    }

}
