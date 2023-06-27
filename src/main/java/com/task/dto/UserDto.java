package com.task.dto;

import com.task.model.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    UUID id;
    String familyName;
    String middleName;
    String firstName;
    Instant birthday;
    Gender gender;
    Integer age;
    String description;
    Boolean isBlocked;
}
