package com.designCenter.designCenter.dto.loan;

import com.designCenter.designCenter.dto.customer.CustomerReqDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasicLoanReqDto {
    private CustomerReqDto customerRequest;
    private LoanReqDto loanRequest;
}
