package com.example.springboot.presentations.v1;

import com.example.springboot.controller.student.StudentsController;
import com.example.springboot.dto.student.StudentDTO;
import com.example.springboot.gateway.student.IStudentRepository;
import com.example.springboot.gateway.student.IStudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentsController.class)
public class StudentsControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStudentService service;

    @MockBean
    private IStudentRepository repository;

    @Test
    public void getStudentByID_return200() throws Exception {
        this.mockMvc.perform(get("/v1/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getStudentByID_returnExtractlyStudentId() throws Exception {
        when(service.readStudentById(2)).thenReturn(new StudentDTO(2, "Robert", "123452"));
        this.mockMvc.perform(get("/v1/2")).andDo(print()).andExpect(content().string(containsString("\"id\":2")));
    }

    @Test
    public void getStudentByID_returnNull() throws Exception {
        when(service.readStudentById(12)).thenReturn(null);
        this.mockMvc.perform(get("/v1/12")).andDo(print()).andExpect(content().string(containsString("")));
    }

    public static String asJSONString(final Object obj)
    {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
