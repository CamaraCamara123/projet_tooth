package com.tooth.service;

import com.tooth.domain.Professor;
import com.tooth.repository.ProfessorRepository;
import com.tooth.service.dto.ProfessorDTO;
import com.tooth.service.mapper.ProfessorMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.tooth.domain.Professor}.
 */
@Service
@Transactional
public class ProfessorService {

    private final Logger log = LoggerFactory.getLogger(ProfessorService.class);

    private final ProfessorRepository professorRepository;

    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    /**
     * Save a professor.
     *
     * @param professorDTO the entity to save.
     * @return the persisted entity.
     */
    public ProfessorDTO save(ProfessorDTO professorDTO) {
        log.debug("Request to save Professor : {}", professorDTO);
        Professor professor = professorMapper.toEntity(professorDTO);
        professor = professorRepository.save(professor);
        return professorMapper.toDto(professor);
    }

    /**
     * Update a professor.
     *
     * @param professorDTO the entity to save.
     * @return the persisted entity.
     */
    public ProfessorDTO update(ProfessorDTO professorDTO) {
        log.debug("Request to update Professor : {}", professorDTO);
        Professor professor = professorMapper.toEntity(professorDTO);
        professor = professorRepository.save(professor);
        return professorMapper.toDto(professor);
    }

    /**
     * Partially update a professor.
     *
     * @param professorDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProfessorDTO> partialUpdate(ProfessorDTO professorDTO) {
        log.debug("Request to partially update Professor : {}", professorDTO);

        return professorRepository
            .findById(professorDTO.getId())
            .map(existingProfessor -> {
                professorMapper.partialUpdate(existingProfessor, professorDTO);

                return existingProfessor;
            })
            .map(professorRepository::save)
            .map(professorMapper::toDto);
    }

    /**
     * Get all the professors.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProfessorDTO> findAll() {
        log.debug("Request to get all Professors");
        return professorRepository.findAll().stream().map(professorMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the professors with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ProfessorDTO> findAllWithEagerRelationships(Pageable pageable) {
        return professorRepository.findAllWithEagerRelationships(pageable).map(professorMapper::toDto);
    }

    /**
     * Get one professor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProfessorDTO> findOne(Long id) {
        log.debug("Request to get Professor : {}", id);
        return professorRepository.findOneWithEagerRelationships(id).map(professorMapper::toDto);
    }

    /**
     * Delete the professor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Professor : {}", id);
        professorRepository.deleteById(id);
    }
}
