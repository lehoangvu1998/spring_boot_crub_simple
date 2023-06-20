package com.example.springboot.controller.student;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.student.CustomErrorDTO;
import com.example.springboot.dto.student.MessageDTO;
import com.example.springboot.dto.student.StudentDTO;
import com.example.springboot.dto.student.StudentsWrapper;
import com.example.springboot.exception.student.StudentException;
import com.example.springboot.gateway.student.IStudentService;

@RestController
@RequestMapping(path = "/v1/student", produces = "application/xml")
public class StudentsController {

	private final Logger logger = LoggerFactory.getLogger(StudentsController.class);

	private IStudentService iStudentService;

	@Autowired
	public StudentsController(IStudentService iStudentService) {
		this.iStudentService = iStudentService;
	}
	
	@GetMapping("/getAllStudent")
	public ResponseEntity<?> getAllStudent(){
		StudentsWrapper students = new StudentsWrapper();
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		MessageDTO messageDTO = new MessageDTO();
		try {
			list = iStudentService.getAllStudent();
			if (!list.isEmpty()) {
				students.setStudent(list);
				return new ResponseEntity<>(students, HttpStatus.OK);
			}
			messageDTO.setMessage("not found data");
			return new ResponseEntity<>(messageDTO, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<?> readStudentById(@PathVariable("id") int studentId) {
		StudentDTO dto = new StudentDTO();
		try {
			dto = iStudentService.readStudentById(studentId);
			if (dto != null) {
				return new ResponseEntity<>(dto, HttpStatus.OK);
			} else {
				CustomErrorDTO customErrorDTO = new CustomErrorDTO();
				customErrorDTO.setMessage("không thấy");
				return new ResponseEntity<CustomErrorDTO>(customErrorDTO, HttpStatus.NOT_FOUND);
			}
		} catch (StudentException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createStudent")
	public ResponseEntity<?> create(@RequestBody StudentDTO student) {
		MessageDTO messageDTO = new MessageDTO();
		try {
			logger.info("start create student");
			if (student != null) {
				boolean isFlag = iStudentService.create(student);
				if (isFlag) {
					messageDTO.setMessage("Created successfully");
					return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>(messageDTO, HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>(messageDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("Exception :  Error occured inside create student : " + e.getMessage(), e);
			return new ResponseEntity<>(messageDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody StudentDTO student, @PathVariable("id") int studentId) {
		MessageDTO messageDTO = new MessageDTO();
		try {
			boolean isSuccess = iStudentService.update(student, studentId);
			if (isSuccess) {
				messageDTO.setMessage("update student successfully");
				return new ResponseEntity<>(messageDTO, HttpStatus.OK);
			} else {
				messageDTO.setMessage("erorr update student");
				return new ResponseEntity<>(messageDTO, HttpStatus.BAD_REQUEST);
			}
		
		} catch (RuntimeException ex) {
			var errorViewModel = new CustomErrorDTO();
			errorViewModel.setName("ActionResult Put(Student student)");
			errorViewModel.setMessage(ex.getMessage());
			errorViewModel.setSource("getStudent()");
			errorViewModel.setStackTrace(ex.getStackTrace().toString());
			return new ResponseEntity<>(errorViewModel, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			var errorViewModel = new CustomErrorDTO();
			errorViewModel.setName("ActionResult Put(Student student)");
			errorViewModel.setMessage(e.getMessage());
			errorViewModel.setSource("getStudent()");
			errorViewModel.setStackTrace(e.getStackTrace().toString());
			return new ResponseEntity<CustomErrorDTO>(errorViewModel, HttpStatus.BAD_REQUEST);
		} finally {
			
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomErrorDTO> delete(@PathVariable("id") int studentId) {
		boolean isSuccess = false;
		CustomErrorDTO message = new CustomErrorDTO();
		try {
			isSuccess = iStudentService.delete(studentId);
			if (isSuccess) {
				message.setMessage("Delete Successfully");
				return new ResponseEntity<CustomErrorDTO>(message, HttpStatus.NO_CONTENT);
			}
			message.setMessage("no action delete");
			return new ResponseEntity<CustomErrorDTO>(message, HttpStatus.NOT_FOUND);

		} catch (Exception ex) {
			logger.error("Exception :  Error occured inside create student : " + ex.getMessage(), ex);
			return new ResponseEntity<CustomErrorDTO>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
