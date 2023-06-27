package com.task.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserJobInfoDto {
    UUID id;
    CompanyDto company;
    UserDto user;
    String description;
    Boolean isActivity;
}