package com.tooth.service.mapper;

import com.tooth.domain.Groupe;
import com.tooth.domain.Professor;
import com.tooth.service.dto.GroupeDTO;
import com.tooth.service.dto.ProfessorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Groupe} and its DTO {@link GroupeDTO}.
 */
@Mapper(componentModel = "spring")
public interface GroupeMapper extends EntityMapper<GroupeDTO, Groupe> {
    @Mapping(target = "professor", source = "professor", qualifiedByName = "professorId")
    GroupeDTO toDto(Groupe s);

    @Named("professorId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProfessorDTO toDtoProfessorId(Professor professor);
}
