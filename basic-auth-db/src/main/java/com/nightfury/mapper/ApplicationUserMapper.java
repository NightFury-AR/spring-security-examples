package com.nightfury.mapper;

import com.nightfury.entity.ApplicationUser;
import com.nightfury.dto.ApplicationUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationUserMapper {
    ApplicationUser toEntity(ApplicationUserDTO dto);
    ApplicationUserDTO toDTO(ApplicationUser entity);
}
