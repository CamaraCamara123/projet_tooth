package com.tooth.web.rest;

import com.tooth.repository.ProfessorRepository;
import com.tooth.service.ProfessorService;
import com.tooth.service.dto.ProfessorDTO;
import com.tooth.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.tooth.domain.Professor}.
 */
@RestController
@RequestMapping("/api/professors")
public class ProfessorResource {

    private final Logger log = LoggerFactory.getLogger(ProfessorResource.class);

    private static final String ENTITY_NAME = "professor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfessorService professorService;

    private final ProfessorRepository professorRepository;

    public ProfessorResource(ProfessorService professorService, ProfessorRepository professorRepository) {
        this.professorService = professorService;
        this.professorRepository = professorRepository;
    }

    /**
     * {@code POST  /professors} : Create a new professor.
     *
     * @param professorDTO the professorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new professorDTO, or with status {@code 400 (Bad Request)} if the professor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProfessorDTO> createProfessor(@Valid @RequestBody ProfessorDTO professorDTO) throws URISyntaxException {
        log.debug("REST request to save Professor : {}", professorDTO);
        if (professorDTO.getId() != null) {
            throw new BadRequestAlertException("A new professor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfessorDTO result = professorService.save(professorDTO);
        return ResponseEntity
            .created(new URI("/api/professors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /professors/:id} : Updates an existing professor.
     *
     * @param id the id of the professorDTO to save.
     * @param professorDTO the professorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated professorDTO,
     * or with status {@code 400 (Bad Request)} if the professorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the professorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> updateProfessor(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProfessorDTO professorDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Professor : {}, {}", id, professorDTO);
        if (professorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, professorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!professorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProfessorDTO result = professorService.update(professorDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, professorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /professors/:id} : Partial updates given fields of an existing professor, field will ignore if it is null
     *
     * @param id the id of the professorDTO to save.
     * @param professorDTO the professorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated professorDTO,
     * or with status {@code 400 (Bad Request)} if the professorDTO is not valid,
     * or with status {@code 404 (Not Found)} if the professorDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the professorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProfessorDTO> partialUpdateProfessor(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProfessorDTO professorDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Professor partially : {}, {}", id, professorDTO);
        if (professorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, professorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!professorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProfessorDTO> result = professorService.partialUpdate(professorDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, professorDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /professors} : get all the professors.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of professors in body.
     */
    @GetMapping("")
    public List<ProfessorDTO> getAllProfessors(@RequestParam(required = false, defaultValue = "true") boolean eagerload) {
        log.debug("REST request to get all Professors");
        return professorService.findAll();
    }

    /**
     * {@code GET  /professors/:id} : get the "id" professor.
     *
     * @param id the id of the professorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the professorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getProfessor(@PathVariable Long id) {
        log.debug("REST request to get Professor : {}", id);
        Optional<ProfessorDTO> professorDTO = professorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(professorDTO);
    }

    /**
     * {@code DELETE  /professors/:id} : delete the "id" professor.
     *
     * @param id the id of the professorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        log.debug("REST request to delete Professor : {}", id);
        professorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
