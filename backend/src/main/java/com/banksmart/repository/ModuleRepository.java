package com.banksmart.repository;

import com.banksmart.model.Module;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
  Optional<Module> findBySlug(String slug);
}
