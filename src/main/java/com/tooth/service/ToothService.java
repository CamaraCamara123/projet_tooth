package com.tooth.service;

import com.tooth.domain.Tooth;
import com.tooth.repository.ToothRepository;
import com.tooth.service.dto.ToothDTO;
import com.tooth.service.mapper.ToothMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.tooth.domain.Tooth}.
 */
@Service
@Transactional
public class ToothService {

    private final Logger log = LoggerFactory.getLogger(ToothService.class);

    private final ToothRepository toothRepository;

    private final ToothMapper toothMapper;

    public ToothService(ToothRepository toothRepository, ToothMapper toothMapper) {
        this.toothRepository = toothRepository;
        this.toothMapper = toothMapper;
    }

    /**
     * Save a tooth.
     *
     * @param toothDTO the entity to save.
     * @return the persisted entity.
     */
    public ToothDTO save(ToothDTO toothDTO) {
        log.debug("Request to save Tooth : {}", toothDTO);
        Tooth tooth = toothMapper.toEntity(toothDTO);
        tooth = toothRepository.save(tooth);
        return toothMapper.toDto(tooth);
    }

    /**
     * Update a tooth.
     *
     * @param toothDTO the entity to save.
     * @return the persisted entity.
     */
    public ToothDTO update(ToothDTO toothDTO) {
        log.debug("Request to update Tooth : {}", toothDTO);
        Tooth tooth = toothMapper.toEntity(toothDTO);
        tooth = toothRepository.save(tooth);
        return toothMapper.toDto(tooth);
    }

    /**
     * Partially update a tooth.
     *
     * @param toothDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ToothDTO> partialUpdate(ToothDTO toothDTO) {
        log.debug("Request to partially update Tooth : {}", toothDTO);

        return toothRepository
            .findById(toothDTO.getId())
            .map(existingTooth -> {
                toothMapper.partialUpdate(existingTooth, toothDTO);

                return existingTooth;
            })
            .map(toothRepository::save)
            .map(toothMapper::toDto);
    }

    /**
     * Get all the teeth.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ToothDTO> findAll() {
        log.debug("Request to get all Teeth");
        return toothRepository.findAll().stream().map(toothMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tooth by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ToothDTO> findOne(Long id) {
        log.debug("Request to get Tooth : {}", id);
        return toothRepository.findById(id).map(toothMapper::toDto);
    }

    /**
     * Delete the tooth by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Tooth : {}", id);
        toothRepository.deleteById(id);
    }
}
