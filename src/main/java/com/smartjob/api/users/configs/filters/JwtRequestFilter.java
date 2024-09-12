package com.smartjob.api.users.configs.filters;

import com.smartjob.api.users.application.port.in.TokenPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String H2_CONSOLE_REGEX = "(^/h2-console|^/console).*";

    private final TokenPort tokenPort;

    @Value("${security.excluded-urls}")
    private List<String> urls;

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String method = request.getMethod();
        String requestUrl = request.getRequestURI();
        if (requestUrl.matches(H2_CONSOLE_REGEX) ||
                urls.contains(requestUrl) && method.equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");

        if (token != null && tokenPort.validateToken(token)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
        }
    }
}
