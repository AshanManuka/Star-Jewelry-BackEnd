package com.designCenter.designCenter.controller.admin;

import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.customer.CustomerReqDto;
import com.designCenter.designCenter.dto.loan.BasicLoanReqDto;
import com.designCenter.designCenter.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/shopUser")
@RequiredArgsConstructor
@Log4j2
public class GoldLoanController {

    private final LoanService loanService;

    @PostMapping(value = "/make-loan", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newLoan(@RequestBody BasicLoanReqDto reqBody){
        log.info("new Gold Loan for user Nic: {}", reqBody.getCustomerRequest().getNic());
        String name = loanService.makeLoan(reqBody);
        return ResponseEntity.ok(new CommonResponse<>(true,name));
    }



}
