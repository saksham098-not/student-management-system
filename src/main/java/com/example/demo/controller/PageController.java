package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Users;
import com.example.demo.model.Course;
import com.example.demo.repository.UsersRepository;
import com.example.demo.repository.CourseRepository;

@Controller
public class PageController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CourseRepository courseRepository;   // ✅ NEW

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // =========================
    // STUDENTS
    // =========================
    @GetMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("users", usersRepository.findAll());
        return "students";
    }

    @GetMapping("/add-student")
    public String addStudent() {
        return "add-student";
    }

    // =========================
    // COURSES
    // =========================
    @GetMapping("/add-course")
    public String addCoursePage() {
        return "add-course";
    }

    // ✅ FIX FOR YOUR ERROR
    @PostMapping("/courses/form")
    public String saveCourse(@RequestParam String name) {

        Course course = new Course();
        course.setName(name);

        courseRepository.save(course);

        return "redirect:/dashboard";
    }

    // =========================
    // EDIT USER
    // =========================
    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);
        return "edit-student";
    }
}