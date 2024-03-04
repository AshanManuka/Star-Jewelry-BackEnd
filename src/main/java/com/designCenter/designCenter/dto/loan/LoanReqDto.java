package com.designCenter.designCenter.dto.loan;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoanReqDto {
    private String description;
    private String itemDescription;
    private String loanNumber;
    private Double fullWeight;
    private Double goldWeight;
    private Double estimateValue;
    private Double marketValue;
    private Double loanAmount;
    private Double rate;
    private Date endDate;
}
