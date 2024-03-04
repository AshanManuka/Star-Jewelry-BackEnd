package com.designCenter.designCenter.dto.customer;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerReqDto {
    private String nic;
    private String name;
    private String address;
    private Long mobile;
}
