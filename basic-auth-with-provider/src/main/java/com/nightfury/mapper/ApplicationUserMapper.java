package com.nightfury.mapper;

import com.nightfury.dto.ApplicationUserDTO;
import com.nightfury.entity.ApplicationUser;
import com.nightfury.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Slf4j
public abstract class ApplicationUserMapper {
    @Autowired
    private RoleMapper roleMapper;
    @Mapping(target = "userId", ignore = true)
    public abstract ApplicationUser toEntity(ApplicationUserDTO dto);
    public abstract ApplicationUserDTO toDTO(ApplicationUser entity);

    @AfterMapping
    public void mapRoles(@MappingTarget ApplicationUser user, ApplicationUserDTO dto) {
        log.info("RAW_INPUT => "+dto.toString());
        if (null != dto.getRoles() && !dto.getRoles().isEmpty()) {
            List<UserRole> roles = dto.getRoles()
                    .stream()
                    .map(roleMapper::toEntity)
                    .collect(Collectors.toList());
            user.setRoles(roles);
        }
    }
}
