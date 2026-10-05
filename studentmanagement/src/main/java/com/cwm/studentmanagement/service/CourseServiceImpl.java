/*package com.cwm.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwm.studentmanagement.model.Course;
import com.cwm.studentmanagement.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repo;

    // 📌 Get all courses
    @Override
    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    // 📌 Get course by ID (IMPORTANT FOR STUDENT MODULE)
    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    // 📌 Save course
    @Override
    public void saveCourse(Course course) {
        repo.save(course);
    }

    // 📌 Delete course
    @Override
    public void deleteCourse(Long id) {
        repo.deleteById(id);
    }

    // 📌 Search course by name
    @Override
    public List<Course> searchByName(String courseName) {
        return repo.findByCourseNameContainingIgnoreCase(courseName);
    }

    // 📌 Total courses count (dashboard use)
    @Override
    public long getTotalCourses() {
        return repo.count();
    }
}*/