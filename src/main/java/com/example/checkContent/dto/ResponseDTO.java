package com.example.checkContent.dto;

public class ResponseDTO {
    private Long id;
    private String message;
//    private String status;
//    private Long userId;
//    private Long contentId;

    public ResponseDTO() {
    }

    public ResponseDTO(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
