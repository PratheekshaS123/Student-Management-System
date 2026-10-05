/*package com.cwm.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cwm.studentmanagement.model.Course;
import com.cwm.studentmanagement.service.CourseService;
import com.cwm.studentmanagement.service.StudentService;

@Controller
public class CourseController {

    @Autowired
    private CourseService service;

    @Autowired
    private StudentService studentService;

    // 📚 Show all courses
    @GetMapping("/courses")
    public String viewCourses(Model model) {

        model.addAttribute("courses", service.getAllCourses());

        // ⭐ IMPORTANT: needed for student count in UI
        model.addAttribute("studentService", studentService);

        return "courses";
    }

    // ➕ Show add course form
    @GetMapping("/courses/new")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "course-form";
    }

    // 💾 Save course
    @PostMapping("/courses/save")
    public String saveCourse(@ModelAttribute Course course) {
        service.saveCourse(course);
        return "redirect:/courses";
    }

    // ✏️ Edit course
    @GetMapping("/courses/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {

        Course course = service.getCourseById(id);

        if (course == null) {
            return "redirect:/courses";
        }

        model.addAttribute("course", course);
        return "course-form";
    }

    // 🗑️ Delete course
    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return "redirect:/courses";
    }

    // 🔍 Search course
    @GetMapping("/courses/search")
    public String searchCourses(@RequestParam("name") String name, Model model) {

        model.addAttribute("courses", service.searchByName(name));
        model.addAttribute("studentService", studentService);

        return "courses";
    }
}*/

package com.cwm.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cwm.studentmanagement.model.Course;
import com.cwm.studentmanagement.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("/add")
    public String addCoursePage(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }

    /*@PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        service.save(course);
        return "redirect:/courses/list";
    }*/
    
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course, Model model) {

        String result = service.save(course);

        if(result.contains("already")) {
            model.addAttribute("error", result);
            model.addAttribute("course", course);
            return "add-course";
        }

        return "redirect:/courses/list";
    }

    @GetMapping("/list")
    public String courseList(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("courses", service.search(keyword));
        model.addAttribute("keyword", keyword);
        return "course-list";
    }

    @GetMapping("/view/{id}")
    public String viewCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", service.getById(id));
        return "view-course";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", service.getById(id));
        return "edit-course";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute Course course) {
        service.save(course);
        return "redirect:/courses/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/courses/list";
    }
}