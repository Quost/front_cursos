package io.github.mqdev.front_cursos.modules.user.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.mqdev.front_cursos.utils.Authentication;

@Service
public class UserLoginService {
    
    @Value("${host.api.gestao.cursos}")
    private String hostApiGestaoCursos;

    public Authentication login(String username, String password) {
        
        RestTemplate restTemplate = new RestTemplate();

        String url = hostApiGestaoCursos + "/user/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, Authentication.class).getBody();
    }
}
