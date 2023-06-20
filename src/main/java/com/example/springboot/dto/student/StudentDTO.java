package com.example.springboot.dto.student;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Student")
public class StudentDTO {
	
    private Integer id;
	 
    private String studentName;
	 
    private String phone;
    
    public StudentDTO(int id, String studentName, String phone)
    {
        this.id = id;
        this.studentName = studentName;
        this.phone = phone;
    }

    public StudentDTO()
    {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
