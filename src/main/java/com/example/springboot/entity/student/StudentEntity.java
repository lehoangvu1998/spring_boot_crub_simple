package com.example.springboot.entity.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class StudentEntity {
	
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "NAME")
    private String studentName;
    
    @Column(name = "PHONE")
    private String phone;

    public StudentEntity(){}
    public StudentEntity(int studentId, String studentName, String phone)
    {
        this.id = studentId;
        this.studentName = studentName;
        this.phone = phone;
    }

    public int getStudentId() {
        return id;
    }

    public void setStudentId(int studentId) {
        this.id = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
