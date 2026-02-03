package com.banksmart.dto;

public class FormFieldResponse {
  private String label;
  private String guidance;
  private String caution;

  public FormFieldResponse(String label, String guidance, String caution) {
    this.label = label;
    this.guidance = guidance;
    this.caution = caution;
  }

  public String getLabel() {
    return label;
  }

  public String getGuidance() {
    return guidance;
  }

  public String getCaution() {
    return caution;
  }
}
