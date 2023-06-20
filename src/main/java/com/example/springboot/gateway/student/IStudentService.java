package com.example.springboot.gateway.student;

import java.util.List;

import com.example.springboot.dto.student.StudentDTO;
import com.example.springboot.exception.student.StudentException;

public interface IStudentService {
	
    List<StudentDTO> readStudents(int pageNumber, int pageSize, String sort);

    StudentDTO readStudentById(int id) throws StudentException;

    Boolean create(StudentDTO studentDTO) throws StudentException;

    Boolean update(StudentDTO studentDTO, int id) throws StudentException;

    Boolean delete(int id) throws StudentException;
    
    List<StudentDTO> getAllStudent() throws StudentException;
    
 }
