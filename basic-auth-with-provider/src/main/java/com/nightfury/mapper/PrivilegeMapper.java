package com.nightfury.mapper;

import com.nightfury.dto.PrivilegeDTO;
import com.nightfury.entity.UserPrivilege;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PrivilegeMapper {
    @Mapping(target = "privilegeId", ignore = true)
    public abstract UserPrivilege toEntity(PrivilegeDTO dto);
    public abstract PrivilegeDTO toDTO(UserPrivilege entity);
}
