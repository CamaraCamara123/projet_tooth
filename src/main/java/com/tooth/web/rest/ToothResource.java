package com.tooth.web.rest;

import com.tooth.repository.ToothRepository;
import com.tooth.service.ToothService;
import com.tooth.service.dto.ToothDTO;
import com.tooth.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.tooth.domain.Tooth}.
 */
@RestController
@RequestMapping("/api/teeth")
public class ToothResource {

    private final Logger log = LoggerFactory.getLogger(ToothResource.class);

    private static final String ENTITY_NAME = "tooth";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ToothService toothService;

    private final ToothRepository toothRepository;

    public ToothResource(ToothService toothService, ToothRepository toothRepository) {
        this.toothService = toothService;
        this.toothRepository = toothRepository;
    }

    /**
     * {@code POST  /teeth} : Create a new tooth.
     *
     * @param toothDTO the toothDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new toothDTO, or with status {@code 400 (Bad Request)} if the tooth has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ToothDTO> createTooth(@RequestBody ToothDTO toothDTO) throws URISyntaxException {
        log.debug("REST request to save Tooth : {}", toothDTO);
        if (toothDTO.getId() != null) {
            throw new BadRequestAlertException("A new tooth cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ToothDTO result = toothService.save(toothDTO);
        return ResponseEntity
            .created(new URI("/api/teeth/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /teeth/:id} : Updates an existing tooth.
     *
     * @param id the id of the toothDTO to save.
     * @param toothDTO the toothDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toothDTO,
     * or with status {@code 400 (Bad Request)} if the toothDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the toothDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ToothDTO> updateTooth(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ToothDTO toothDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Tooth : {}, {}", id, toothDTO);
        if (toothDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toothDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toothRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ToothDTO result = toothService.update(toothDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toothDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /teeth/:id} : Partial updates given fields of an existing tooth, field will ignore if it is null
     *
     * @param id the id of the toothDTO to save.
     * @param toothDTO the toothDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toothDTO,
     * or with status {@code 400 (Bad Request)} if the toothDTO is not valid,
     * or with status {@code 404 (Not Found)} if the toothDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the toothDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ToothDTO> partialUpdateTooth(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ToothDTO toothDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Tooth partially : {}, {}", id, toothDTO);
        if (toothDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toothDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toothRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ToothDTO> result = toothService.partialUpdate(toothDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toothDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /teeth} : get all the teeth.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of teeth in body.
     */
    @GetMapping("")
    public List<ToothDTO> getAllTeeth() {
        log.debug("REST request to get all Teeth");
        return toothService.findAll();
    }

    /**
     * {@code GET  /teeth/:id} : get the "id" tooth.
     *
     * @param id the id of the toothDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toothDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ToothDTO> getTooth(@PathVariable Long id) {
        log.debug("REST request to get Tooth : {}", id);
        Optional<ToothDTO> toothDTO = toothService.findOne(id);
        return ResponseUtil.wrapOrNotFound(toothDTO);
    }

    /**
     * {@code DELETE  /teeth/:id} : delete the "id" tooth.
     *
     * @param id the id of the toothDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTooth(@PathVariable Long id) {
        log.debug("REST request to delete Tooth : {}", id);
        toothService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
