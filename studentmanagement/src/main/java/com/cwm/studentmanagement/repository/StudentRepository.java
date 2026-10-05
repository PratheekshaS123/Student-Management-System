/*package com.cwm.studentmanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cwm.studentmanagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByNameContainingIgnoreCase(String name);

    long countByCourseId(Long courseId);
}*/

/*package com.cwm.studentmanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cwm.studentmanagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstNameContainingIgnoreCase(String firstName);
}*/


package com.cwm.studentmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwm.studentmanagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstNameContainingIgnoreCase(String firstName);

    Optional<Student> findByEmail(String email);
}