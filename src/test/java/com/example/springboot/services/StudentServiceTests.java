package com.example.springboot.services;

import com.example.springboot.dto.student.StudentDTO;
import com.example.springboot.gateway.student.IStudentRepository;
import com.example.springboot.service.student.StudentService;

import org.aspectj.lang.annotation.Before;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTests {
    @MockBean
    IStudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    public StudentServiceTests()
    {

    }

//    @Before
//    public void setUp() {
//        Mockito.when(studentRepository.findAll())
//                .thenReturn(IntStream.range(0, 10)
//                        .mapToObj(i -> new StudentDTO(i, "title-" + i, "detail-" + i))
//                        .collect(Collectors.toList()));


}
