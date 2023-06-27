package com.task.controller;

import com.task.dto.UserJobInfoDto;
import com.task.exception.AlreadyExists;
import com.task.exception.NotFound;
import com.task.service.UserJobInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    final UserJobInfoService service;

    @GetMapping(value = "/get-userjob", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<List<UserJobInfoDto>> get(UserJobInfoDto parameter) {
        if (parameter == null) {
            return ResponseEntity.badRequest().body(null);
        }
        List<UserJobInfoDto> list = service.find(parameter);
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/create-userjob", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(@RequestBody UserJobInfoDto parameter) {
        try {
            service.addUserJobInfo(parameter);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (AlreadyExists ex) {
            log.error(ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @PatchMapping(value = "/update-userjob", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserJobInfoDto> update(@RequestBody UserJobInfoDto parameter) {
        try {
            var result = service.updateUserJobInfo(parameter);
            return ResponseEntity.ok().body(result);
        } catch (NotFound ex) {
            log.error(ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
