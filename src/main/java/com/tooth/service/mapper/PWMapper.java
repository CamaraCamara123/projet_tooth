package com.tooth.service.mapper;

import com.tooth.domain.Groupe;
import com.tooth.domain.PW;
import com.tooth.domain.Tooth;
import com.tooth.service.dto.GroupeDTO;
import com.tooth.service.dto.PWDTO;
import com.tooth.service.dto.ToothDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PW} and its DTO {@link PWDTO}.
 */
@Mapper(componentModel = "spring")
public interface PWMapper extends EntityMapper<PWDTO, PW> {
    @Mapping(target = "tooth", source = "tooth", qualifiedByName = "toothName")
    @Mapping(target = "groupes", source = "groupes", qualifiedByName = "groupeCodeSet")
    PWDTO toDto(PW s);

    @Mapping(target = "removeGroupe", ignore = true)
    PW toEntity(PWDTO pWDTO);

    @Named("toothName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ToothDTO toDtoToothName(Tooth tooth);

    @Named("groupeCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    GroupeDTO toDtoGroupeCode(Groupe groupe);

    @Named("groupeCodeSet")
    default Set<GroupeDTO> toDtoGroupeCodeSet(Set<Groupe> groupe) {
        return groupe.stream().map(this::toDtoGroupeCode).collect(Collectors.toSet());
    }
}
