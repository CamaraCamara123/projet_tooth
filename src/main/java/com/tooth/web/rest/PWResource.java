package com.tooth.web.rest;

import com.tooth.repository.PWRepository;
import com.tooth.security.AuthoritiesConstants;
import com.tooth.service.PWService;
import com.tooth.service.dto.PWDTO;
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
 * REST controller for managing {@link com.tooth.domain.PW}.
 */
@RestController
@RequestMapping("/api/pws")
public class PWResource {

    private final Logger log = LoggerFactory.getLogger(PWResource.class);

    private static final String ENTITY_NAME = "pW";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PWService pWService;

    private final PWRepository pWRepository;

    public PWResource(PWService pWService, PWRepository pWRepository) {
        this.pWService = pWService;
        this.pWRepository = pWRepository;
    }

    /**
     * {@code POST  /pws} : Create a new pW.
     *
     * @param pWDTO the pWDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pWDTO, or with status {@code 400 (Bad Request)} if the pW has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PWDTO> createPW(@Valid @RequestBody PWDTO pWDTO) throws URISyntaxException {
        log.debug("REST request to save PW : {}", pWDTO);
        if (pWDTO.getId() != null) {
            throw new BadRequestAlertException("A new pW cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PWDTO result = pWService.save(pWDTO);
        return ResponseEntity
            .created(new URI("/api/pws/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pws/:id} : Updates an existing pW.
     *
     * @param id the id of the pWDTO to save.
     * @param pWDTO the pWDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pWDTO,
     * or with status {@code 400 (Bad Request)} if the pWDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pWDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PWDTO> updatePW(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody PWDTO pWDTO)
        throws URISyntaxException {
        log.debug("REST request to update PW : {}, {}", id, pWDTO);
        if (pWDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pWDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pWRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PWDTO result = pWService.update(pWDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pWDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pws/:id} : Partial updates given fields of an existing pW, field will ignore if it is null
     *
     * @param id the id of the pWDTO to save.
     * @param pWDTO the pWDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pWDTO,
     * or with status {@code 400 (Bad Request)} if the pWDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pWDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pWDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PWDTO> partialUpdatePW(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PWDTO pWDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PW partially : {}, {}", id, pWDTO);
        if (pWDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pWDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pWRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PWDTO> result = pWService.partialUpdate(pWDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pWDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pws} : get all the pWS.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pWS in body.
     */
    @GetMapping("")
    public List<PWDTO> getAllPWS(@RequestParam(required = false, defaultValue = "true") boolean eagerload) {
        log.debug("REST request to get all PWS");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            // Check if the user has the admin role
            boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN));

            if (isAdmin) {
                return pWService.findAll();
            } else {
                String username = authentication.getName();
                boolean isStudent = authentication.getAuthorities().contains(new SimpleGrantedAuthority(AuthoritiesConstants.STUDENT));
                boolean isProf = authentication.getAuthorities().contains(new SimpleGrantedAuthority(AuthoritiesConstants.PROFESSOR));
                if (isStudent) {
                    return pWService.findPWByStudent(username);
                } else if (isProf) {
                    return pWService.findAll();
                }
            }
        } else {
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    /**
     * {@code GET  /pws/:id} : get the "id" pW.
     *
     * @param id the id of the pWDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pWDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PWDTO> getPW(@PathVariable Long id) {
        log.debug("REST request to get PW : {}", id);
        Optional<PWDTO> pWDTO = pWService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pWDTO);
    }

    /**
     * {@code DELETE  /pws/:id} : delete the "id" pW.
     *
     * @param id the id of the pWDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePW(@PathVariable Long id) {
        log.debug("REST request to delete PW : {}", id);
        pWService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
