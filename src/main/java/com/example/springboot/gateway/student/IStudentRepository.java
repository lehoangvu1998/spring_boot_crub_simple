package com.example.springboot.gateway.student;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.student.StudentEntity;
import com.example.springboot.exception.student.StudentException;

@Repository
public interface IStudentRepository extends CrudRepository<StudentEntity, Integer>{
	
	  @Query(value = "SELECT COUNT(*) FROM STUDENTS.STUDENTS st WHERE st.PHONE = ?1", nativeQuery = true)
	  Integer checkPhone (String phone);
	  
	  @Query(value = "SELECT * FROM STUDENTS.STUDENTS", nativeQuery = true)
	  List<StudentEntity> getAllStudent() throws StudentException ;
	  
	  @Query(value = "SELECT COUNT(*) FROM STUDENTS.STUDENTS st WHERE st.ID = ?1", nativeQuery = true)
	  Integer checkStudentExisted(String id);
	  
	  @Query(value = "SELECT * FROM STUDENTS.STUDENTS st WHERE st.ID = ?1", nativeQuery = true)
	  StudentEntity getStudentById(int id);
}
