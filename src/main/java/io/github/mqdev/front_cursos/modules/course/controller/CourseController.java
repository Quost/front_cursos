package io.github.mqdev.front_cursos.modules.course.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.mqdev.front_cursos.modules.course.dto.CourseDTO;
import io.github.mqdev.front_cursos.modules.course.services.CreateCourseService;
import io.github.mqdev.front_cursos.modules.course.services.GetCategoriesService;
import io.github.mqdev.front_cursos.modules.course.services.GetCoursesService;
import io.github.mqdev.front_cursos.utils.Auth;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private GetCoursesService getCoursesService;

    @Autowired
    private GetCategoriesService getCategoriesService;

    @Autowired
    private CreateCourseService createCourseService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public String listCourses(Model model) {

        try {
            List<CourseDTO> courses = getCoursesService.getCourses(Auth.getToken());
            model.addAttribute("courses", courses);
        } catch (HttpClientErrorException e) {
            return "redirect:/user/login";
        }
        return "course/list";
    }

    @GetMapping("/create")
    public String getCreateCoursePage(Model model) {
        List<String> categories = getCategoriesService.execute(Auth.getToken());
        model.addAttribute("categories", categories);
        model.addAttribute("course", new CourseDTO());
        return "course/register";
    }

    @PostMapping("/create")
    public String createCourse(CourseDTO course, Model model) {
        List<String> categories = getCategoriesService.execute(Auth.getToken());

        try {
            createCourseService.execute(Auth.getToken(), course);
            return "redirect:/course/list";
        } catch (Exception e) {
            model.addAttribute("categories", categories);
            model.addAttribute("course", course);
            model.addAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            return "course/register";
        }
    }
}
