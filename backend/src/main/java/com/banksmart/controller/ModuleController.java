package com.banksmart.controller;

import com.banksmart.dto.ModuleResponse;
import com.banksmart.service.ModuleService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {
  private final ModuleService moduleService;

  public ModuleController(ModuleService moduleService) {
    this.moduleService = moduleService;
  }

  @GetMapping
  public ResponseEntity<List<ModuleResponse>> getModules() {
    return ResponseEntity.ok(moduleService.getAllModules());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ModuleResponse> getModule(@PathVariable Long id) {
    return ResponseEntity.ok(moduleService.getModule(id));
  }
}
