package com.banksmart.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Module {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String slug;
  private String title;
  private String purpose;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> requiredDocuments = new ArrayList<>();

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> processSteps = new ArrayList<>();

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> commonMistakes = new ArrayList<>();

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> formHighlights = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public List<String> getRequiredDocuments() {
    return requiredDocuments;
  }

  public void setRequiredDocuments(List<String> requiredDocuments) {
    this.requiredDocuments = requiredDocuments;
  }

  public List<String> getProcessSteps() {
    return processSteps;
  }

  public void setProcessSteps(List<String> processSteps) {
    this.processSteps = processSteps;
  }

  public List<String> getCommonMistakes() {
    return commonMistakes;
  }

  public void setCommonMistakes(List<String> commonMistakes) {
    this.commonMistakes = commonMistakes;
  }

  public List<String> getFormHighlights() {
    return formHighlights;
  }

  public void setFormHighlights(List<String> formHighlights) {
    this.formHighlights = formHighlights;
  }
}
