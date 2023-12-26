package com.tooth.service.mapper;

import com.tooth.domain.Tooth;
import com.tooth.service.dto.ToothDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tooth} and its DTO {@link ToothDTO}.
 */
@Mapper(componentModel = "spring")
public interface ToothMapper extends EntityMapper<ToothDTO, Tooth> {}
