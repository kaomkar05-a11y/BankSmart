package com.banksmart.service;

import com.banksmart.dto.ProgressResponse;
import com.banksmart.model.Module;
import com.banksmart.model.User;
import com.banksmart.model.UserProgress;
import com.banksmart.repository.ModuleRepository;
import com.banksmart.repository.UserProgressRepository;
import com.banksmart.repository.UserRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {
  private final UserProgressRepository userProgressRepository;
  private final UserRepository userRepository;
  private final ModuleRepository moduleRepository;

  public ProgressService(UserProgressRepository userProgressRepository,
      UserRepository userRepository, ModuleRepository moduleRepository) {
    this.userProgressRepository = userProgressRepository;
    this.userRepository = userRepository;
    this.moduleRepository = moduleRepository;
  }

  public List<ProgressResponse> getProgress(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    return userProgressRepository.findByUser(user).stream()
        .map(progress -> new ProgressResponse(
            progress.getModule().getId(),
            progress.getStatus(),
            progress.getCompletedAt()))
        .toList();
  }

  public ProgressResponse completeModule(String email, Long moduleId) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    Module module = moduleRepository.findById(moduleId)
        .orElseThrow(() -> new IllegalArgumentException("Module not found"));
    UserProgress progress = userProgressRepository.findByUserAndModule(user, module)
        .orElseGet(UserProgress::new);
    progress.setUser(user);
    progress.setModule(module);
    progress.setStatus("COMPLETED");
    progress.setCompletedAt(Instant.now());
    userProgressRepository.save(progress);
    return new ProgressResponse(module.getId(), progress.getStatus(), progress.getCompletedAt());
  }
}
