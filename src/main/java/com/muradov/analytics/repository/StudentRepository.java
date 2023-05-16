package com.muradov.analytics.repository;

import com.muradov.analytics.model.LibraryApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<LibraryApp,Integer> {
}
