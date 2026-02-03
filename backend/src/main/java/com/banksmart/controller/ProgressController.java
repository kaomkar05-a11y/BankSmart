package com.banksmart.controller;

import com.banksmart.dto.ProgressResponse;
import com.banksmart.service.ProgressService;
import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {
  private final ProgressService progressService;

  public ProgressController(ProgressService progressService) {
    this.progressService = progressService;
  }

  @GetMapping
  public ResponseEntity<List<ProgressResponse>> getProgress(Principal principal) {
    return ResponseEntity.ok(progressService.getProgress(principal.getName()));
  }

  @PostMapping("/{moduleId}/complete")
  public ResponseEntity<ProgressResponse> completeModule(@PathVariable Long moduleId, Principal principal) {
    return ResponseEntity.ok(progressService.completeModule(principal.getName(), moduleId));
  }
}
