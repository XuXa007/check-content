package com.example.checkContent.dto;

public class ResponseDTO {
    private Long id;
    private String message;
    private String status;
    private Long userId;
    private Long contentId;

    public ResponseDTO() {
    }

    public ResponseDTO(Long id, String message, String status, Long userId, Long contentId) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.userId = userId;
        this.contentId = contentId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }
}
