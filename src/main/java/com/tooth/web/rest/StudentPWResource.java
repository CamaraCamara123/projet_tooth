package com.tooth.web.rest;

import com.tooth.repository.StudentPWRepository;
import com.tooth.security.AuthoritiesConstants;
import com.tooth.service.StudentPWService;
import com.tooth.service.dto.StudentPWDTO;
import com.tooth.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.tooth.domain.StudentPW}.
 */
@RestController
@RequestMapping("/api/student-pws")
public class StudentPWResource {

    private final Logger log = LoggerFactory.getLogger(StudentPWResource.class);

    private static final String ENTITY_NAME = "studentPW";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StudentPWService studentPWService;

    private final StudentPWRepository studentPWRepository;

    public StudentPWResource(StudentPWService studentPWService, StudentPWRepository studentPWRepository) {
        this.studentPWService = studentPWService;
        this.studentPWRepository = studentPWRepository;
    }

    /**
     * {@code POST  /student-pws} : Create a new studentPW.
     *
     * @param studentPWDTO the studentPWDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new studentPWDTO, or with status {@code 400 (Bad Request)} if the studentPW has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<StudentPWDTO> createStudentPW(@Valid @RequestBody StudentPWDTO studentPWDTO) throws URISyntaxException {
        log.debug("REST request to save StudentPW : {}", studentPWDTO);
        if (studentPWDTO.getId() != null) {
            throw new BadRequestAlertException("A new studentPW cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentPWDTO result = studentPWService.save(studentPWDTO);
        return ResponseEntity
            .created(new URI("/api/student-pws/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /student-pws/:id} : Updates an existing studentPW.
     *
     * @param id the id of the studentPWDTO to save.
     * @param studentPWDTO the studentPWDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated studentPWDTO,
     * or with status {@code 400 (Bad Request)} if the studentPWDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the studentPWDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudentPWDTO> updateStudentPW(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody StudentPWDTO studentPWDTO
    ) throws URISyntaxException {
        log.debug("REST request to update StudentPW : {}, {}", id, studentPWDTO);
        if (studentPWDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, studentPWDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!studentPWRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StudentPWDTO result = studentPWService.update(studentPWDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, studentPWDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /student-pws/:id} : Partial updates given fields of an existing studentPW, field will ignore if it is null
     *
     * @param id the id of the studentPWDTO to save.
     * @param studentPWDTO the studentPWDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated studentPWDTO,
     * or with status {@code 400 (Bad Request)} if the studentPWDTO is not valid,
     * or with status {@code 404 (Not Found)} if the studentPWDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the studentPWDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StudentPWDTO> partialUpdateStudentPW(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody StudentPWDTO studentPWDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update StudentPW partially : {}, {}", id, studentPWDTO);
        if (studentPWDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, studentPWDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!studentPWRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StudentPWDTO> result = studentPWService.partialUpdate(studentPWDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, studentPWDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /student-pws} : get all the studentPWS.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of studentPWS in body.
     */
    @GetMapping("")
    public List<StudentPWDTO> getAllStudentPWS(@RequestParam(required = false, defaultValue = "true") boolean eagerload) {
        log.debug("REST request to get all StudentPWS");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            // Check if the user has the admin role
            boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN));

            if (isAdmin) {
                return studentPWService.findAll();
            } else {
                String username = authentication.getName();
                boolean isStudent = authentication.getAuthorities().contains(new SimpleGrantedAuthority(AuthoritiesConstants.STUDENT));
                if (isStudent) {
                    return studentPWService.findPWByStudent(username);
                }
            }
        } else {
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    /**
     * {@code GET  /student-pws/:id} : get the "id" studentPW.
     *
     * @param id the id of the studentPWDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the studentPWDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentPWDTO> getStudentPW(@PathVariable Long id) {
        log.debug("REST request to get StudentPW : {}", id);
        Optional<StudentPWDTO> studentPWDTO = studentPWService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studentPWDTO);
    }

    /**
     * {@code DELETE  /student-pws/:id} : delete the "id" studentPW.
     *
     * @param id the id of the studentPWDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentPW(@PathVariable Long id) {
        log.debug("REST request to delete StudentPW : {}", id);
        studentPWService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
