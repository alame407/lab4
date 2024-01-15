package com.alame.lab4.repository;

import com.alame.lab4.model.CheckedAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CheckedAttemptRepository extends JpaRepository<CheckedAttempt, Long> {
}
