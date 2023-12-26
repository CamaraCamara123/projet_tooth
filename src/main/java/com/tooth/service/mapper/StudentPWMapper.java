package com.tooth.service.mapper;

import com.tooth.domain.PW;
import com.tooth.domain.Student;
import com.tooth.domain.StudentPW;
import com.tooth.service.dto.PWDTO;
import com.tooth.service.dto.StudentDTO;
import com.tooth.service.dto.StudentPWDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StudentPW} and its DTO {@link StudentPWDTO}.
 */
@Mapper(componentModel = "spring")
public interface StudentPWMapper extends EntityMapper<StudentPWDTO, StudentPW> {
    @Mapping(target = "student", source = "student", qualifiedByName = "studentId")
    @Mapping(target = "pw", source = "pw", qualifiedByName = "pWTitle")
    StudentPWDTO toDto(StudentPW s);

    @Named("studentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StudentDTO toDtoStudentId(Student student);

    @Named("pWTitle")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    PWDTO toDtoPWTitle(PW pW);
}
