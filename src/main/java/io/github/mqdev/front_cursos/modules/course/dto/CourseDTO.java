package io.github.mqdev.front_cursos.modules.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    
    private String name;
    private String category;
    private String status;
    private String teacher;
}
