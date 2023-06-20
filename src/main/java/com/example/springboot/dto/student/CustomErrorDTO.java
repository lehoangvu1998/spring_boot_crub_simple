package com.example.springboot.dto.student;

public class CustomErrorDTO {
    public CustomErrorDTO(String name, String source, String message, String stackTrace)
    {
        this.name = name;
        this.source = source;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public CustomErrorDTO()
    {}

    private String name;
    private String source;
    private String message;
    private String stackTrace;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
