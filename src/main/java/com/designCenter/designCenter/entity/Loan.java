package com.designCenter.designCenter.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String itemDescription;
    @Column(unique = true, nullable = false)
    private String loanNumber;
    @Column(nullable = false)
    private Double fullWeight;
    @Column(nullable = false)
    private Double goldWeight;
    @Column(nullable = false)
    private Double estimateValue;
    @Column(nullable = false)
    private Double marketValue;
    @Column(nullable = false)
    private Double initialLoanAmount;
    @Column(nullable = false)
    private Double updatedLoanAmount;
    @Column(nullable = false)
    private Double rate;
    @Column(nullable = false)
    private Double totalBalance;
    @Column(nullable = false)
    private Double currentPayed;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "loan")
    @ToString.Exclude
    private List<Enrollment> enrollmentList = new ArrayList<>();

    @OneToMany(mappedBy = "loan")
    @ToString.Exclude
    private List<LoanDetail> loanDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "loan")
    @ToString.Exclude
    private List<Interest> interestList = new ArrayList<>();

}
