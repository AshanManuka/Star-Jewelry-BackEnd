package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.loan.BasicLoanReqDto;

public interface LoanService {

    String makeLoan(BasicLoanReqDto reqBody);
}
