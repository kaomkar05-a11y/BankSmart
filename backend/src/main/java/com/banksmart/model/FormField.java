package com.banksmart.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class FormField {
  private String label;
  private String guidance;
  private String caution;

  public FormField() {
  }

  public FormField(String label, String guidance, String caution) {
    this.label = label;
    this.guidance = guidance;
    this.caution = caution;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getGuidance() {
    return guidance;
  }

  public void setGuidance(String guidance) {
    this.guidance = guidance;
  }

  public String getCaution() {
    return caution;
  }

  public void setCaution(String caution) {
    this.caution = caution;
  }
}
