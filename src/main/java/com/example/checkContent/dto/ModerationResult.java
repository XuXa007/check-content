package com.example.checkContent.dto;

import java.io.Serializable;

public class ModerationResult implements Serializable {
    private Long contentId;
    private String status;
    private String mes;

    public ModerationResult(Long contentId, String status, String mes) {
        this.contentId = contentId;
        this.status = status;
        this.mes = mes;
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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
