package com.example.checkContent.dto;

public class ResponseDTO {
    private String message;
    private Long contentId;
    private Long userId;

    public ResponseDTO() {
    }

    public ResponseDTO(String message, Long contentId, Long userId) {
        this.message = message;
        this.contentId = contentId;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
