package com.nightfury.dto;

import com.nightfury.constants.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDTO implements Serializable {
private String roleId;
private ROLE role;
private List<PrivilegeDTO> userPrivileges;
}
