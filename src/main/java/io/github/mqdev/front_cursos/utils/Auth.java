package io.github.mqdev.front_cursos.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Auth {

    public static String getToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getDetails().toString();
    }
}
