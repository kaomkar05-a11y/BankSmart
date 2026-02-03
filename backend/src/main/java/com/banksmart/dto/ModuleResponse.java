package com.banksmart.dto;

import java.util.List;

public class ModuleResponse {
  private Long id;
  private String slug;
  private String title;
  private String purpose;
  private List<String> requiredDocuments;
  private List<String> processSteps;
  private List<String> commonMistakes;
  private List<String> formHighlights;
  private List<FormFieldResponse> formFields;

  public ModuleResponse(Long id, String slug, String title, String purpose,
      List<String> requiredDocuments, List<String> processSteps,
      List<String> commonMistakes, List<String> formHighlights, List<FormFieldResponse> formFields) {
    this.id = id;
    this.slug = slug;
    this.title = title;
    this.purpose = purpose;
    this.requiredDocuments = requiredDocuments;
    this.processSteps = processSteps;
    this.commonMistakes = commonMistakes;
    this.formHighlights = formHighlights;
    this.formFields = formFields;
  }

  public Long getId() {
    return id;
  }

  public String getSlug() {
    return slug;
  }

  public String getTitle() {
    return title;
  }

  public String getPurpose() {
    return purpose;
  }

  public List<String> getRequiredDocuments() {
    return requiredDocuments;
  }

  public List<String> getProcessSteps() {
    return processSteps;
  }

  public List<String> getCommonMistakes() {
    return commonMistakes;
  }

  public List<String> getFormHighlights() {
    return formHighlights;
  }

  public List<FormFieldResponse> getFormFields() {
    return formFields;
  }
}
