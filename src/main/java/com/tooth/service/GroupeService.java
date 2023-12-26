package com.tooth.service;

import com.tooth.domain.Groupe;
import com.tooth.repository.GroupeRepository;
import com.tooth.service.dto.GroupeDTO;
import com.tooth.service.mapper.GroupeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.tooth.domain.Groupe}.
 */
@Service
@Transactional
public class GroupeService {

    private final Logger log = LoggerFactory.getLogger(GroupeService.class);

    private final GroupeRepository groupeRepository;

    private final GroupeMapper groupeMapper;

    public GroupeService(GroupeRepository groupeRepository, GroupeMapper groupeMapper) {
        this.groupeRepository = groupeRepository;
        this.groupeMapper = groupeMapper;
    }

    /**
     * Save a groupe.
     *
     * @param groupeDTO the entity to save.
     * @return the persisted entity.
     */
    public GroupeDTO save(GroupeDTO groupeDTO) {
        log.debug("Request to save Groupe : {}", groupeDTO);
        Groupe groupe = groupeMapper.toEntity(groupeDTO);
        groupe = groupeRepository.save(groupe);
        return groupeMapper.toDto(groupe);
    }

    /**
     * Update a groupe.
     *
     * @param groupeDTO the entity to save.
     * @return the persisted entity.
     */
    public GroupeDTO update(GroupeDTO groupeDTO) {
        log.debug("Request to update Groupe : {}", groupeDTO);
        Groupe groupe = groupeMapper.toEntity(groupeDTO);
        groupe = groupeRepository.save(groupe);
        return groupeMapper.toDto(groupe);
    }

    /**
     * Partially update a groupe.
     *
     * @param groupeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GroupeDTO> partialUpdate(GroupeDTO groupeDTO) {
        log.debug("Request to partially update Groupe : {}", groupeDTO);

        return groupeRepository
            .findById(groupeDTO.getId())
            .map(existingGroupe -> {
                groupeMapper.partialUpdate(existingGroupe, groupeDTO);

                return existingGroupe;
            })
            .map(groupeRepository::save)
            .map(groupeMapper::toDto);
    }

    /**
     * Get all the groupes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GroupeDTO> findAll() {
        log.debug("Request to get all Groupes");
        return groupeRepository.findAll().stream().map(groupeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one groupe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GroupeDTO> findOne(Long id) {
        log.debug("Request to get Groupe : {}", id);
        return groupeRepository.findById(id).map(groupeMapper::toDto);
    }

    /**
     * Delete the groupe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Groupe : {}", id);
        groupeRepository.deleteById(id);
    }
}
