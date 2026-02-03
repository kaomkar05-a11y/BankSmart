package com.banksmart.dto;

import java.time.Instant;

public class ProgressResponse {
  private Long moduleId;
  private String status;
  private Instant completedAt;

  public ProgressResponse(Long moduleId, String status, Instant completedAt) {
    this.moduleId = moduleId;
    this.status = status;
    this.completedAt = completedAt;
  }

  public Long getModuleId() {
    return moduleId;
  }

  public String getStatus() {
    return status;
  }

  public Instant getCompletedAt() {
    return completedAt;
  }
}
