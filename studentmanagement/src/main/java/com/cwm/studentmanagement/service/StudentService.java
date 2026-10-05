/*package com.cwm.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwm.studentmanagement.model.Student;
import com.cwm.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // 📌 Get all students
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // 📌 Save student
    public void saveStudent(Student student) {
        repo.save(student);
    }

    // 📌 Get by ID
    public Student getStudentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // 📌 Delete student
    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }

    // 📌 Count students
    public long getStudentCount() {
        return repo.count();
    }

    // 🔍 Search by name
    public List<Student> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    // 🔍 Check name exists
    public boolean isNameExists(String name) {
        return !repo.findByName(name).isEmpty();
    }

    // 📊 Count by course
    public long countStudentsByCourse(Long courseId) {
        return repo.countByCourseId(courseId);
    }
}*/ 

/*package com.cwm.studentmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwm.studentmanagement.model.Student;
import com.cwm.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> getAll() {
        return repo.findAll();
    }

    public void save(Student s) {
        repo.save(s);
    }

    public Student getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Student> search(String name) {
        return repo.findByFirstNameContainingIgnoreCase(name);
    }
}*/

package com.cwm.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwm.studentmanagement.model.Student;
import com.cwm.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> getAll() {
        return repo.findAll();
    }

    public String save(Student s) {

        Student existingStudent =
                repo.findByEmail(s.getEmail()).orElse(null);

        // INSERT
        if (s.getId() == null) {

            if (existingStudent != null) {
                return "Student already exists";
            }
        }

        // UPDATE
        else {

            if (existingStudent != null &&
                !existingStudent.getId().equals(s.getId())) {

                return "Email already exists";
            }
        }

        repo.save(s);
        return "Student saved successfully";
    }

    public Student getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Student> search(String name) {
        return repo.findByFirstNameContainingIgnoreCase(name);
    }
}