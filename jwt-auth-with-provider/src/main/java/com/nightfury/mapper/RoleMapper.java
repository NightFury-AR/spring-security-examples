package com.nightfury.mapper;

import com.nightfury.dto.RoleDTO;
import com.nightfury.entity.UserPrivilege;
import com.nightfury.entity.UserRole;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = MapperConfiguration.class)
public abstract class RoleMapper {

    @Autowired private PrivilegeMapper privilegeMapper;

    @Mapping(target = "roleId", ignore = true)
    public abstract UserRole toEntity(RoleDTO dto);
    public abstract RoleDTO toDTO(UserRole entity);

    @AfterMapping
    public void mapRoles(@MappingTarget UserRole entity, RoleDTO dto) {
        if (!dto.getUserPrivileges().isEmpty()) {
            List<UserPrivilege> privileges = dto.getUserPrivileges()
                    .stream()
                    .map(privilegeMapper::toEntity)
                    .collect(Collectors.toList());
            entity.setUserPrivileges(privileges);
        }
    }
}
