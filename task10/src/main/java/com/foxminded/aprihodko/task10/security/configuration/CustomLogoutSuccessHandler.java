package com.foxminded.aprihodko.task10.security.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.foxminded.aprihodko.task10.models.MyUserPrincipal;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        String username = principal.getUsername();
        logger.debug(String.format("Successfully %s auto logged in", username));
    }
}
