package com.banksmart.service;

import com.banksmart.dto.FormFieldResponse;
import com.banksmart.dto.ModuleResponse;
import com.banksmart.model.Module;
import com.banksmart.repository.ModuleRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {
  private final ModuleRepository moduleRepository;

  public ModuleService(ModuleRepository moduleRepository) {
    this.moduleRepository = moduleRepository;
  }

  public List<ModuleResponse> getAllModules() {
    return moduleRepository.findAll().stream().map(this::toResponse).toList();
  }

  public ModuleResponse getModule(Long id) {
    Module module = moduleRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Module not found"));
    return toResponse(module);
  }

  private ModuleResponse toResponse(Module module) {
    return new ModuleResponse(
        module.getId(),
        module.getSlug(),
        module.getTitle(),
        module.getPurpose(),
        module.getRequiredDocuments(),
        module.getProcessSteps(),
        module.getCommonMistakes(),
        module.getFormHighlights(),
        module.getFormFields().stream()
            .map(field -> new FormFieldResponse(field.getLabel(), field.getGuidance(), field.getCaution()))
            .toList());
  }
}
