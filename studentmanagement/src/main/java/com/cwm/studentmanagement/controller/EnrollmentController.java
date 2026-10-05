/*package com.cwm.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cwm.studentmanagement.model.Course;
import com.cwm.studentmanagement.model.Enrollment;
import com.cwm.studentmanagement.model.Student;
import com.cwm.studentmanagement.service.CourseService;
import com.cwm.studentmanagement.service.EnrollmentService;
import com.cwm.studentmanagement.service.StudentService;

@Controller
public class EnrollmentController {

    @Autowired
    private StudentService ss;

    @Autowired
    private CourseService cs;

    @Autowired
    private EnrollmentService es;

    @GetMapping("/enroll")
    public String page(Model m) {
        m.addAttribute("students", ss.getAll());
        m.addAttribute("courses", cs.getAll());
        return "enroll";
    }

    @PostMapping("/saveEnroll")
    public String save(@RequestParam Long studentId,
                       @RequestParam Long courseId,
                       @RequestParam String enrollDate) {

        Student student = ss.getById(studentId);
        Course course = cs.getById(courseId);

        if (student == null || course == null) {
            return "redirect:/enroll?error=true";
        }

        Enrollment e = new Enrollment();
        e.setStudent(student);
        e.setCourse(course);
        e.setEnrollDate(enrollDate);

        es.save(e);

        return "redirect:/enroll?success=true";
    }
}*/


package com.cwm.studentmanagement.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cwm.studentmanagement.model.Course;
import com.cwm.studentmanagement.model.Enrollment;
import com.cwm.studentmanagement.model.Student;
import com.cwm.studentmanagement.service.CourseService;
import com.cwm.studentmanagement.service.EnrollmentService;
import com.cwm.studentmanagement.service.StudentService;

@Controller
public class EnrollmentController {

    @Autowired
    private StudentService ss;

    @Autowired
    private CourseService cs;

    @Autowired
    private EnrollmentService es;

    @GetMapping("/enroll")
    public String page(Model m) {
        m.addAttribute("students", ss.getAll());
        m.addAttribute("courses", cs.getAll());
        return "enroll";
    }

    @PostMapping("/saveEnroll")
    public String save(@RequestParam Long studentId,
                       @RequestParam Long courseId,
                       @RequestParam String enrollDate) {

        Student student = ss.getById(studentId);
        Course course = cs.getById(courseId);

        if (student == null || course == null) {
            return "redirect:/enroll";
        }

        if (es.exists(studentId, courseId)) {
            return "redirect:/enroll?duplicate=true";
        }

        Enrollment e = new Enrollment();
        e.setStudent(student);
        e.setCourse(course);
        e.setEnrollDate(enrollDate);

        es.save(e);

        return "redirect:/enrolled-students";
    }

    @GetMapping("/enrolled-students")
    public String enrolledStudents(
            @RequestParam(required = false) String keyword,
            Model m) {

        List<Enrollment> enrollments;

        if (keyword != null && !keyword.trim().isEmpty()) {
            enrollments = es.search(keyword);
        } else {
            enrollments = es.getAll();
        }

        Map<Long, Enrollment> uniqueStudents = new LinkedHashMap<>();

        for (Enrollment e : enrollments) {

            if (e.getStudent() == null || e.getCourse() == null) {
                continue;
            }

            Long studentId = e.getStudent().getId();

            if (!uniqueStudents.containsKey(studentId)) {
                uniqueStudents.put(studentId, e);
            }
        }

        m.addAttribute("students", uniqueStudents.values());
        m.addAttribute("keyword", keyword);

        return "enrolled-students";
    }

    @GetMapping("/enrollment-details/{studentId}")
    public String details(@PathVariable Long studentId, Model m) {

        Student student = ss.getById(studentId);

        List<Enrollment> enrollments = es.getByStudentId(studentId);

        double totalFee = 0.0;

        for (Enrollment e : enrollments) {
            if (e.getCourse() != null && e.getCourse().getCourseFee() != null) {
                totalFee += e.getCourse().getCourseFee();
            }
        }

        m.addAttribute("student", student);
        m.addAttribute("enrollments", enrollments);
        m.addAttribute("totalFee", totalFee);

        return "enrollment-details";
    }

    @GetMapping("/delete-enrolled-course/{studentId}/{courseId}")
    public String deleteCourse(@PathVariable Long studentId,
                               @PathVariable Long courseId) {

        es.deleteCourse(studentId, courseId);

        return "redirect:/enrollment-details/" + studentId;
    }
}