package com.task.controller;

import com.task.dto.UserJobInfoDto;
import com.task.service.UserJobInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Import({ProjectInfoAutoConfiguration.class})
@WebMvcTest(MainController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MainControllerTest {
    @MockBean
    private UserJobInfoService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findTest() throws Exception {
        var userId = UUID.randomUUID();
        this.mockMvc.perform(get("/get-userjob?user.id=" + userId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
        doAnswer(invocationOnMock -> {
            var test = (UserJobInfoDto) invocationOnMock.getArgument(0);
            assertThat(test)
                    .hasFieldOrPropertyWithValue("user.id", userId)
                    .hasFieldOrPropertyWithValue("company", null);
            return null;
        }).when(service).find(any(UserJobInfoDto.class));
        verify(service, times(1)).find(any(UserJobInfoDto.class));
    }

    @Test
    void createTest() throws Exception {
        this.mockMvc.perform(post("/create-userjob")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "description": "test",  
                                  "user": {"firstName": "a"},
                                  "company": {"companyName": "b"}
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());
        doAnswer(invocationOnMock -> {
            var test = (UserJobInfoDto) invocationOnMock.getArgument(0);
            assertThat(test)
                    .hasFieldOrPropertyWithValue("company.companyName", "b")
                    .hasFieldOrPropertyWithValue("user.firstName", "a")
                    .hasFieldOrPropertyWithValue("description", "test");
            return null;
        }).when(service).addUserJobInfo(any(UserJobInfoDto.class));
        verify(service, times(1)).addUserJobInfo(any(UserJobInfoDto.class));
    }

    @Test
    void updateTest() throws Exception {
        var id = UUID.randomUUID();
        var userId = UUID.randomUUID();
        var companyId = UUID.randomUUID();

        this.mockMvc.perform(patch("/update-userjob")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "id": "%s",  
                                  "description": "test",  
                                  "user": {"id": "%s", "firstName": "a"},
                                  "company": {"id": "%s", "companyName": "b"}
                                }
                                """.formatted(id, userId, companyId)))
                .andDo(print())
                .andExpect(status().isOk());
        doAnswer(invocationOnMock -> {
            var test = (UserJobInfoDto) invocationOnMock.getArgument(0);
            assertThat(test)
                    .hasFieldOrPropertyWithValue("id", id)
                    .hasFieldOrPropertyWithValue("company.id", companyId)
                    .hasFieldOrPropertyWithValue("user.id", userId)
                    .hasFieldOrPropertyWithValue("company.companyName", "b")
                    .hasFieldOrPropertyWithValue("user.firstName", "a")
                    .hasFieldOrPropertyWithValue("description", "test");
            return null;
        }).when(service).updateUserJobInfo(any(UserJobInfoDto.class));
        verify(service, times(1)).updateUserJobInfo(any(UserJobInfoDto.class));
    }

}
