package com.example.checkContent.dto;

import java.io.Serializable;

public class ModerationResult implements Serializable {
    private Long contentId;
    private String status;
    private String message;

    public ModerationResult(Long contentId, String status, String message) {
        this.contentId = contentId;
        this.status = status;
        this.message = message;
    }

    public ModerationResult() {
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
