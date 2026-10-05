/*package com.cwm.studentmanagement.service;

import java.util.List;
import com.cwm.studentmanagement.model.Course;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    void saveCourse(Course course);

    void deleteCourse(Long id);

    List<Course> searchByName(String courseName);

    long getTotalCourses();
}*/


/*package com.cwm.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwm.studentmanagement.model.Course;
import com.cwm.studentmanagement.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    public void save(Course course) {
        repo.save(course);
    }

    public List<Course> getAll() {
        return repo.findAll();
    }

    public Course getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Course> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll();
        }
        return repo.findByCourseNameContainingIgnoreCase(keyword);
    }
   
}*/


/*package com.cwm.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwm.studentmanagement.model.Course;
import com.cwm.studentmanagement.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    public List<Course> getAll() {
        return repo.findAll();
    }

    public void save(Course course) {
        repo.save(course);
    }

    public Course getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Course> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll();
        }
        return repo.findByCourseNameContainingIgnoreCase(keyword);
    }
}*/

package com.cwm.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwm.studentmanagement.model.Course;
import com.cwm.studentmanagement.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    public List<Course> getAll() {
        return repo.findAll();
    }

    public String save(Course course) {

        // Check duplicate course name
        Course existingCourse =
                repo.findByCourseName(course.getCourseName()).orElse(null);

        // Check duplicate course code
        Course existingCode =
                repo.findByCourseCode(course.getCourseCode()).orElse(null);

        // For INSERT
        if (course.getId() == null) {

            if (existingCourse != null) {
                return "Course name already exists";
            }

            if (existingCode != null) {
                return "Course code already exists";
            }
        }

        // For UPDATE
        else {

            if (existingCourse != null &&
                !existingCourse.getId().equals(course.getId())) {

                return "Course name already exists";
            }

            if (existingCode != null &&
                !existingCode.getId().equals(course.getId())) {

                return "Course code already exists";
            }
        }

        repo.save(course);
        return "Course saved successfully";
    }

    public Course getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Course> search(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll();
        }

        return repo.findByCourseNameContainingIgnoreCase(keyword);
    }
}