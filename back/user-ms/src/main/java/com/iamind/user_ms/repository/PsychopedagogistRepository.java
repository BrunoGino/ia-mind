package com.iamind.user_ms.repository;

import com.iamind.user_ms.model.Psychopedagogist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsychopedagogistRepository extends JpaRepository<Psychopedagogist, Long> {
}
