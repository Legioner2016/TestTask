package com.task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {
    UUID id;
    String companyName;
    String description;
    Boolean isActivity;
}