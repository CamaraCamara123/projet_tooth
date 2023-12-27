package com.tooth.repository;

import com.tooth.domain.Groupe;
import com.tooth.domain.Professor;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Groupe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long> {
    @Query("SELECT g FROM Groupe g WHERE g.professor.user.login = :username")
    List<Groupe> findGroupesByUsername(@Param("username") String username);

    List<Groupe> findGroupesByProfessorId(Long id);
}
