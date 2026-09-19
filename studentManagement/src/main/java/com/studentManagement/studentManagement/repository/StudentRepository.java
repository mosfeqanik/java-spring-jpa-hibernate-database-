package com.studentManagement.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.studentManagement.studentManagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}