package com.example.springboot.service.student;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.controller.student.StudentsController;
import com.example.springboot.dto.student.StudentDTO;
import com.example.springboot.entity.student.StudentEntity;
import com.example.springboot.exception.student.StudentException;
import com.example.springboot.gateway.student.IStudentRepository;
import com.example.springboot.gateway.student.IStudentService;

@Service
public class StudentService implements IStudentService {

	private final Logger logger = LoggerFactory.getLogger(StudentsController.class);

	private IStudentRepository iStudentRepository;

	private String specialChar = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z])$";

	private String numberValid = "^[0-9]*$";

	@Autowired
	public StudentService(IStudentRepository iStudentRepository) {
		this.iStudentRepository = iStudentRepository;
	}

	@Override
	public List<StudentDTO> readStudents(int pageNumber, int pageSize, String sort) {
		try {
			logger.info("start get all student ");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public StudentDTO readStudentById(int id) throws StudentException {

		StudentDTO dto = new StudentDTO();
		try {
			logger.info("start get student by id service");
			StudentEntity entity = iStudentRepository.getStudentById(id);
			if (entity != null) {
				dto = convertEntityToDTO(entity);
				return dto;
			}
			return null;
		} catch (Exception e) {
			logger.error("Exception :  Error occured inside delete student : " + e.getMessage(), e);
			throw new StudentException("Exception :  Error occured inside delete  student : " + e.getMessage());
		}
	}

	@Override
	public Boolean update(StudentDTO studentDTO, int id) throws StudentException {
		try {
			logger.info("start update student service");
			Integer isFlag = iStudentRepository.checkStudentExisted(String.valueOf(id));
			if (isFlag > 0) {
				boolean isInformation = validateInfo(studentDTO);
				if (isInformation) {
					iStudentRepository.save(converDTOtoEntity(studentDTO));
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (Exception e) {
			logger.error("Exception :  Error occured inside delete student : " + e.getMessage(), e);
			throw new StudentException("Exception :  Error occured inside delete  student : " + e.getMessage());
		}
	}

	@Override
	public Boolean delete(int id) throws StudentException {
		try {
			logger.info("start delete student service");
			Integer isFlag = iStudentRepository.checkStudentExisted(String.valueOf(id));
			if (isFlag > 0) {
				iStudentRepository.deleteById(id);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("Exception :  Error occured inside delete student : " + e.getMessage(), e);
			throw new StudentException("Exception :  Error occured inside delete  student : " + e.getMessage());
		}
	}

	@Override
	public Boolean create(StudentDTO studentDTO) throws StudentException {
		try {
			
			logger.info("start create student service");
			boolean isFlag = validateInfo(studentDTO);
			if (isFlag) {
				StudentEntity studentEntity = converDTOtoEntity(studentDTO);
				Integer checkPhoneExisted = iStudentRepository.checkPhone(studentEntity.getPhone());
				if (checkPhoneExisted <= 0) {
					iStudentRepository.save(studentEntity);
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (Exception e) {
			logger.error("Exception :  Error occured inside create student : " + e.getMessage(), e);
			throw new StudentException("Exception :  Error occured inside create student : " + e.getMessage());
		}

	}

	@Override
	public List<StudentDTO> getAllStudent() throws StudentException {
		logger.info("start get all student ");
		List<StudentDTO> studentDTOs = new ArrayList<>();
		try {
			List<StudentEntity> entity = new ArrayList<>();
			entity = iStudentRepository.getAllStudent();
			if (entity != null) {
				studentDTOs = convertEntityToDTOList(entity);
				return studentDTOs;
			}
			return null;
		} catch (Exception e) {
			logger.error("Exception :  Error occured inside get all student : " + e.getMessage(), e);
			throw new StudentException("Exception :  Error occured inside get all student : " + e.getMessage());
		}
	}

	// validate Information
	private boolean validateInfo(StudentDTO studentDTO) {
		if (studentDTO.getStudentName().length() <= 1 || !studentDTO.getStudentName().matches(specialChar)) {
			return false;
		}
		if ((!studentDTO.getPhone().matches(numberValid))
				|| (studentDTO.getPhone().length() < 10 || studentDTO.getPhone().length() > 15)
				|| (studentDTO.getPhone().matches(specialChar))) {
			return false;
		}
		return true;
	}

	private StudentDTO convertEntityToDTO(StudentEntity student) {
		StudentDTO dto = new StudentDTO();
		dto.setId(student.getStudentId());
		dto.setStudentName(student.getStudentName());
		dto.setPhone(student.getPhone());
		return dto;
	}

	private StudentEntity converDTOtoEntity(StudentDTO studentDTO) {
		StudentEntity entity = new StudentEntity();
		entity.setStudentName(studentDTO.getStudentName());
		entity.setPhone(studentDTO.getPhone());
		return entity;
	}

	private List<StudentDTO> convertEntityToDTOList(List<StudentEntity> entities) {
		List<StudentDTO> studentDTOs = new ArrayList<>();
		for (StudentEntity student : entities) {
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setId(student.getStudentId());
			studentDTO.setStudentName(student.getStudentName());
			studentDTO.setPhone(student.getPhone());
			studentDTOs.add(studentDTO);
		}
		return studentDTOs;
	}
}
