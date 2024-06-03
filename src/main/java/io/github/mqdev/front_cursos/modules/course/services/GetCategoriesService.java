package io.github.mqdev.front_cursos.modules.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;

@Service
public class GetCategoriesService {

    @Value("${host.api.gestao.cursos}")
    private String hostApiGestaoCursos;

    public List<String> execute(String token){
        try {

            RestTemplate restTemplate = new RestTemplate();

            String url = hostApiGestaoCursos + "/cursos/categorias";

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);

            var request = new HttpEntity<>(headers);

            ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<List<String>>() {
            };

            var result = restTemplate.exchange(url, HttpMethod.GET, request, responseType).getBody();
            return result;
        } catch (Unauthorized e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Token inválido");
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar categorias");
        }
    }
    
}
