package com.tooth.service.mapper;

import com.tooth.domain.Groupe;
import com.tooth.domain.Student;
import com.tooth.domain.User;
import com.tooth.service.dto.GroupeDTO;
import com.tooth.service.dto.StudentDTO;
import com.tooth.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userLogin")
    @Mapping(target = "groupe", source = "groupe", qualifiedByName = "groupeCode")
    StudentDTO toDto(Student s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("groupeCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    GroupeDTO toDtoGroupeCode(Groupe groupe);
}
