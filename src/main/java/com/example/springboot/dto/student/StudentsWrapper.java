package com.example.springboot.dto.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "Students")
public class StudentsWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @JacksonXmlProperty(localName = "student")
	 @JacksonXmlElementWrapper(useWrapping = false)
	public List<StudentDTO> student = new ArrayList<>();
	
	public List<StudentDTO> getStudent() {
		return student;
	}
	public void setStudent(List<StudentDTO> student) {
		this.student = student;
	}
	
}
