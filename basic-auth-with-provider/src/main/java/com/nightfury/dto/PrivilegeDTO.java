package com.nightfury.dto;

import com.nightfury.constants.Privilege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrivilegeDTO implements Serializable {
    private Long privilegeId;
    private Privilege privilege;
}
