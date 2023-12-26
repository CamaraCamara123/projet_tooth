package com.tooth.repository;

import com.tooth.domain.Tooth;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Tooth entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ToothRepository extends JpaRepository<Tooth, Long> {}
