package com.banksmart.repository;

import com.banksmart.model.Module;
import com.banksmart.model.User;
import com.banksmart.model.UserProgress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
  List<UserProgress> findByUser(User user);
  Optional<UserProgress> findByUserAndModule(User user, Module module);
}
