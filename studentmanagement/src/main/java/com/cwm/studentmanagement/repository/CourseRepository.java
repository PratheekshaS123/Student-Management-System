/*package com.cwm.studentmanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cwm.studentmanagement.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCourseNameContainingIgnoreCase(String courseName);

}*/

/*package com.cwm.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwm.studentmanagement.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCourseNameContainingIgnoreCase(String keyword);
}*/

package com.cwm.studentmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwm.studentmanagement.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCourseNameContainingIgnoreCase(String keyword);

    Optional<Course> findByCourseName(String courseName);

    Optional<Course> findByCourseCode(String courseCode);
}