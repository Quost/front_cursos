package io.github.mqdev.front_cursos.modules.course.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import io.github.mqdev.front_cursos.modules.course.dto.CourseDTO;

@Service
public class CreateCourseService {

    @Value("${host.api.gestao.cursos}")
    private String hostApiGestaoCursos;

    public void execute(String token, CourseDTO course) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = hostApiGestaoCursos + "/cursos";

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);

            var request = new HttpEntity<>(course, headers);

            restTemplate.postForObject(url, request, CourseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar curso");
        }
    }
}
