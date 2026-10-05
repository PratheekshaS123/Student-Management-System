/*package com.cwm.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cwm.studentmanagement.model.Student;
import com.cwm.studentmanagement.model.Course;
import com.cwm.studentmanagement.service.StudentService;
import com.cwm.studentmanagement.service.CourseService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    // 📊 DASHBOARD
    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("totalStudents", studentService.getStudentCount());
        model.addAttribute("totalCourses", courseService.getTotalCourses());

        return "dashboard";
    }

    // 📄 VIEW STUDENTS PAGE
    @GetMapping("/students")
    public String viewStudents(Model model) {

        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("totalStudents", studentService.getStudentCount());

        return "students";
    }

    // ➕ SHOW ADD STUDENT FORM
    @GetMapping("/addStudent")
    public String showAddForm(Model model) {

        model.addAttribute("student", new Student());

        // send course list for dropdown
        model.addAttribute("courses", courseService.getAllCourses());

        return "add-student";
    }

    // 💾 SAVE STUDENT (🔥 FIXED VERSION)
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student,
                              @RequestParam Long courseId) {

        // ✅ fetch real course from DB (VERY IMPORTANT)
        Course course = courseService.getCourseById(courseId);

        // ✅ attach course to student
        student.setCourse(course);

        // save student
        studentService.saveStudent(student);

        return "redirect:/students";
    }

    // ✏️ EDIT STUDENT
    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable Long id, Model model) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            return "redirect:/students";
        }

        model.addAttribute("student", student);
        model.addAttribute("courses", courseService.getAllCourses());

        return "add-student";
    }

    // ❌ DELETE STUDENT
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/students";
    }

    // 🔍 SEARCH STUDENT
    @GetMapping("/students/search")
    public String searchStudent(@RequestParam(value = "name", required = false) String name,
                                Model model) {

        if (name == null || name.isEmpty()) {
            model.addAttribute("students", studentService.getAllStudents());
        } else {
            model.addAttribute("students", studentService.searchByName(name));
        }

        return "students";
    }
}*/

/*package com.cwm.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cwm.studentmanagement.model.Student;
import com.cwm.studentmanagement.service.StudentService;
import com.cwm.studentmanagement.service.EnrollmentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private CourseService courseService;

    // ---------------- DASHBOARD ----------------
    @GetMapping("/")
    public String dashboard(Model m) {
        m.addAttribute("students", service.getAll());
        m.addAttribute("totalStudents", service.getAll().size());
        m.addAttribute("totalCourses", courseService.getAll().size());
        m.addAttribute("topCourse", enrollmentService.getTopCourse());
        m.addAttribute("monthlyEnrollments", enrollmentService.getMonthlyEnrollments());
        return "dashboard";
    }

    // ---------------- STUDENT LIST ----------------
    @GetMapping("/students")
    public String students(Model m) {
        m.addAttribute("students", service.getAll());
        return "students";
    }

    // ---------------- ADD STUDENT PAGE ----------------
    @GetMapping("/addStudent")
    public String addStudentForm(Model m) {
        m.addAttribute("student", new Student());
        return "add-student";
    }

    // ---------------- SAVE (INSERT + UPDATE) ----------------
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students";
    }

    // ---------------- VIEW STUDENT ----------------
    @GetMapping("/viewStudent/{id}")
    public String viewStudent(@PathVariable Long id, Model m) {

        Student student = service.getById(id);

        if (student == null) {
            return "redirect:/students";
        }

        m.addAttribute("student", student);
        return "view_student";
    }

    // ---------------- EDIT STUDENT ----------------
    @GetMapping("/editStudent/{id}")
    public String edit(@PathVariable Long id, Model m) {

        Student s = service.getById(id);

        if (s == null) {
            return "redirect:/students";
        }

        m.addAttribute("student", s);
        return "edit-student";
    }

    // ---------------- DELETE STUDENT ----------------
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {

        enrollmentService.deleteByStudentId(id); // delete child records first
        service.delete(id);                      // delete student

        return "redirect:/students";
    }

    // ---------------- SEARCH STUDENT ----------------
    @GetMapping("/students/search")
    public String searchStudents(@RequestParam String name, Model m) {

        m.addAttribute("students", service.search(name));
        return "students";
    }
}*/


package com.cwm.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cwm.studentmanagement.model.Student;
import com.cwm.studentmanagement.service.StudentService;
import com.cwm.studentmanagement.service.EnrollmentService;
import com.cwm.studentmanagement.service.CourseService;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CourseService courseService;

    // ---------------- DASHBOARD ----------------
    @GetMapping("/")
    public String dashboard(Model m) {
        m.addAttribute("students", service.getAll());
        m.addAttribute("totalStudents", service.getAll().size());
        m.addAttribute("totalCourses", courseService.getAll().size());
        m.addAttribute("topCourse", enrollmentService.getTopCourse());
        m.addAttribute("monthlyEnrollments", enrollmentService.getMonthlyEnrollments());
        return "dashboard";
    }

    // ---------------- STUDENT LIST ----------------
    @GetMapping("/students")
    public String students(Model m) {
        m.addAttribute("students", service.getAll());
        return "students";
    }

    // ---------------- ADD STUDENT PAGE ----------------
    @GetMapping("/addStudent")
    public String addStudentForm(Model m) {
        m.addAttribute("student", new Student());
        return "add-student";
    }

    // ---------------- SAVE (INSERT + UPDATE) ----------------
   /* @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students";
    }*/
    
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student, Model m) {

        String result = service.save(student);

        if(result.contains("already")) {
            m.addAttribute("error", result);
            m.addAttribute("student", student);
            return "add-student";
        }

        return "redirect:/students";
    }

    // ---------------- VIEW STUDENT ----------------
    @GetMapping("/viewStudent/{id}")
    public String viewStudent(@PathVariable Long id, Model m) {
        Student student = service.getById(id);

        if (student == null) {
            return "redirect:/students";
        }

        m.addAttribute("student", student);
        return "view_student";
    }

    // ---------------- EDIT STUDENT ----------------
    @GetMapping("/editStudent/{id}")
    public String edit(@PathVariable Long id, Model m) {
        Student s = service.getById(id);

        if (s == null) {
            return "redirect:/students";
        }

        m.addAttribute("student", s);
        return "edit-student";
    }

    // ---------------- DELETE STUDENT ----------------
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        enrollmentService.deleteByStudentId(id);
        service.delete(id);
        return "redirect:/students";
    }

    // ---------------- SEARCH STUDENT ----------------
    @GetMapping("/students/search")
    public String searchStudents(@RequestParam String name, Model m) {
        m.addAttribute("students", service.search(name));
        return "students";
    }
}


