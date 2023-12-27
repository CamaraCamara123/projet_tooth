package com.tooth.service;

import com.tooth.domain.PW;
import com.tooth.domain.Student;
import com.tooth.domain.StudentPW;
import com.tooth.repository.PWRepository;
import com.tooth.repository.StudentRepository;
import com.tooth.service.dto.*;
import com.tooth.service.mapper.PWMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.tooth.domain.PW}.
 */
@Service
@Transactional
public class PWService {

    private final Logger log = LoggerFactory.getLogger(PWService.class);

    private final PWRepository pWRepository;

    private final PWMapper pWMapper;

    @Autowired
    StudentRepository studentRepository;

    public PWService(PWRepository pWRepository, PWMapper pWMapper) {
        this.pWRepository = pWRepository;
        this.pWMapper = pWMapper;
    }

    /**
     * Save a pW.
     *
     * @param pWDTO the entity to save.
     * @return the persisted entity.
     */
    public PWDTO save(PWDTO pWDTO) {
        log.debug("Request to save PW : {}", pWDTO);
        PW pW = pWMapper.toEntity(pWDTO);
        pW = pWRepository.save(pW);
        return pWMapper.toDto(pW);
    }

    /**
     * Update a pW.
     *
     * @param pWDTO the entity to save.
     * @return the persisted entity.
     */
    public PWDTO update(PWDTO pWDTO) {
        log.debug("Request to update PW : {}", pWDTO);
        PW pW = pWMapper.toEntity(pWDTO);
        pW = pWRepository.save(pW);
        return pWMapper.toDto(pW);
    }

    /**
     * Partially update a pW.
     *
     * @param pWDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PWDTO> partialUpdate(PWDTO pWDTO) {
        log.debug("Request to partially update PW : {}", pWDTO);

        return pWRepository
            .findById(pWDTO.getId())
            .map(existingPW -> {
                pWMapper.partialUpdate(existingPW, pWDTO);

                return existingPW;
            })
            .map(pWRepository::save)
            .map(pWMapper::toDto);
    }

    /**
     * Get all the pWS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PWDTO> findAll() {
        log.debug("Request to get all PWS");
        return pWRepository.findAll().stream().map(pWMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the pWS with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<PWDTO> findAllWithEagerRelationships(Pageable pageable) {
        return pWRepository.findAllWithEagerRelationships(pageable).map(pWMapper::toDto);
    }

    /**
     * Get one pW by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PWDTO> findOne(Long id) {
        log.debug("Request to get PW : {}", id);
        return pWRepository.findOneWithEagerRelationships(id).map(pWMapper::toDto);
    }

    /**
     * Delete the pW by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PW : {}", id);
        pWRepository.deleteById(id);
    }

    public List<PWDTO> findPWByStudent(String username) {
        Student student = studentRepository.findStudentByUserName(username);
        Set<PW> PWS = student.getGroupe().getPws();

        return PWS
            .stream()
            .map(p -> {
                PWDTO pt = new PWDTO();
                pt.setId(p.getId());
                pt.setTitle(p.getTitle());
                pt.setDocs(p.getDocs());
                pt.setObjectif(p.getObjectif());
                pt.setDocsContentType(p.getDocsContentType());

                if (p.getTooth() != null) {
                    ToothDTO t = new ToothDTO();
                    t.setId(p.getTooth().getId());
                    t.setName(p.getTooth().getName());
                    pt.setTooth(t);
                }

                return pt;
            })
            .collect(Collectors.toList());
    }
}
