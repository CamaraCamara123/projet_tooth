package com.tooth.service.mapper;

import com.tooth.domain.Professor;
import com.tooth.domain.User;
import com.tooth.service.dto.ProfessorDTO;
import com.tooth.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Professor} and its DTO {@link ProfessorDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProfessorMapper extends EntityMapper<ProfessorDTO, Professor> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userLogin")
    ProfessorDTO toDto(Professor s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);
}
