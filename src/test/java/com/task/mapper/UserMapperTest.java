package com.task.mapper;

import com.task.dto.UserDto;
import com.task.model.Gender;
import com.task.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    UserMapper userMapper;

    @Test
    void toDto() {
        var model = User.builder()
                .id(UUID.randomUUID())
                .isBlocked(false)
                .description("test")
                .middleName("b")
                .age(15)
                .gender(Gender.MALE)
                .firstName("a")
                .birthday(Instant.now())
                .familyName("c")
                .build();
        assertThat(userMapper.toDto(model))
                .hasFieldOrPropertyWithValue("isBlocked", false)
                .hasFieldOrPropertyWithValue("description", "test")
                .hasFieldOrPropertyWithValue("middleName", "b")
                .hasFieldOrPropertyWithValue("firstName", "a")
                .hasFieldOrPropertyWithValue("familyName", "c")
                .hasFieldOrPropertyWithValue("age", 15)
                .hasFieldOrPropertyWithValue("gender", Gender.MALE)
                .hasFieldOrProperty("id").isNotNull()
                .hasFieldOrProperty("birthday").isNotNull();
    }

    @Test
    void toModel() {
        var dto = new UserDto();
        dto.setId(UUID.randomUUID());
        dto.setIsBlocked(false);
        dto.setGender(Gender.MALE);
        dto.setMiddleName("b");
        dto.setBirthday(Instant.now());
        dto.setFirstName("a");
        dto.setFamilyName("c");
        dto.setDescription("test");
        dto.setAge(15);
        assertThat(userMapper.toModel(dto))
                .hasFieldOrPropertyWithValue("isBlocked", false)
                .hasFieldOrPropertyWithValue("description", "test")
                .hasFieldOrPropertyWithValue("middleName", "b")
                .hasFieldOrPropertyWithValue("firstName", "a")
                .hasFieldOrPropertyWithValue("familyName", "c")
                .hasFieldOrPropertyWithValue("age", 15)
                .hasFieldOrPropertyWithValue("gender", Gender.MALE)
                .hasFieldOrProperty("id").isNotNull()
                .hasFieldOrProperty("birthday").isNotNull();
    }
}