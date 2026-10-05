

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwm.studentmanagement.model.Enrollment;
import com.cwm.studentmanagement.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repo;

    public void save(Enrollment e) {
        repo.save(e);
    }

    // 🔥 FIXED (TRANSACTION REQUIRED)
    @Transactional
    public void deleteByStudentId(Long studentId) {
        repo.deleteByStudentId(studentId);
    }
}*/

/*package com.cwm.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwm.studentmanagement.model.Enrollment;
import com.cwm.studentmanagement.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repo;

    public void save(Enrollment e) {
        repo.save(e);
    }

    public List<Enrollment> getAll() {
        return repo.findAll();
    }

    public List<Enrollment> getByStudentId(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    public boolean exists(Long studentId, Long courseId) {
        return repo.existsByStudentIdAndCourseId(studentId, courseId);
    }

    public List<Enrollment> search(String name) {
        return repo.findByStudentFirstNameContainingIgnoreCase(name);
    }

    @Transactional
    public void deleteByStudentId(Long studentId) {
        repo.deleteByStudentId(studentId);
    }

    @Transactional
    public void deleteCourse(Long studentId, Long courseId) {
        repo.deleteByStudentIdAndCourseId(studentId, courseId);
    }
}*/



package com.cwm.studentmanagement.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwm.studentmanagement.model.Enrollment;
import com.cwm.studentmanagement.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repo;

    public void save(Enrollment e) {
        repo.save(e);
    }

    public List<Enrollment> getAll() {
        return repo.findAll();
    }

    public List<Enrollment> getByStudentId(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    public boolean exists(Long studentId, Long courseId) {
        return repo.existsByStudentIdAndCourseId(studentId, courseId);
    }

    public List<Enrollment> search(String name) {
        return repo.findByStudentFirstNameContainingIgnoreCase(name);
    }

    @Transactional
    public void deleteByStudentId(Long studentId) {
        repo.deleteByStudentId(studentId);
    }

    @Transactional
    public void deleteCourse(Long studentId, Long courseId) {
        repo.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    public long getMonthlyEnrollments() {

        LocalDate now = LocalDate.now();
        long count = 0;

        for (Enrollment e : repo.findAll()) {

            if (e.getEnrollDate() == null || e.getEnrollDate().isEmpty()) {
                continue;
            }

            LocalDate date = LocalDate.parse(e.getEnrollDate());

            if (date.getMonthValue() == now.getMonthValue()
                    && date.getYear() == now.getYear()) {
                count++;
            }
        }

        return count;
    }

    public String getTopCourse() {

        List<Enrollment> enrollments = repo.findAll();

        if (enrollments.isEmpty()) {
            return "N/A";
        }

        Map<String, Integer> countMap = new HashMap<>();

        for (Enrollment e : enrollments) {

            if (e.getCourse() == null) {
                continue;
            }

            String courseName = e.getCourse().getCourseName();

            countMap.put(courseName,
                    countMap.getOrDefault(courseName, 0) + 1);
        }

        String topCourse = "N/A";
        int max = 0;

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {

            if (entry.getValue() > max) {
                max = entry.getValue();
                topCourse = entry.getKey();
            }
        }

        return topCourse;
    }
}