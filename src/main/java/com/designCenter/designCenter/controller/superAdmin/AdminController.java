package com.designCenter.designCenter.controller.superAdmin;

import com.designCenter.designCenter.dto.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/superUser")
@Log4j2
@RequiredArgsConstructor
public class AdminController {

    @GetMapping(value = "/hello")
    public ResponseEntity<?> sample(){
        log.info("Check API");
        return ResponseEntity.ok(new CommonResponse<>(true,"Welcome Super User...!"));
    }
}
