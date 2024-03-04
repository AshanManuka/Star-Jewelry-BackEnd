package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.dto.loan.BasicLoanReqDto;
import com.designCenter.designCenter.entity.Customer;
import com.designCenter.designCenter.entity.Interest;
import com.designCenter.designCenter.entity.Loan;
import com.designCenter.designCenter.entity.LoanDetail;
import com.designCenter.designCenter.enums.Type;
import com.designCenter.designCenter.repository.CustomerRepository;
import com.designCenter.designCenter.repository.InterestRepository;
import com.designCenter.designCenter.repository.LoanDetailRepository;
import com.designCenter.designCenter.repository.LoanRepository;
import com.designCenter.designCenter.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class LoanServiceImpl implements LoanService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final LoanRepository loanRepository;
    private final LoanDetailRepository loanDetailRepository;
    private final InterestRepository interestRepository;

    @Override
    @Transactional
    public String makeLoan(BasicLoanReqDto reqBody) {

        try {
            log.info("check the customer is exists cusNic: {}",reqBody.getCustomerRequest().getNic());
            Customer tempCustomer = customerRepository.findCustomerByNic(reqBody.getCustomerRequest().getNic());
            if(tempCustomer != null){
                tempCustomer = modelMapper.map(reqBody.getCustomerRequest(),Customer.class);
                tempCustomer.setEdited(new Date());
                tempCustomer = customerRepository.save(tempCustomer);
                log.info("Updated customer details of NIC:{}",tempCustomer.getNic());
            }else{
                tempCustomer = modelMapper.map(reqBody.getCustomerRequest(),Customer.class);
                tempCustomer = customerRepository.save(tempCustomer);
                log.info("Saved new customer details of NIC:{}",tempCustomer.getNic());
            }

            // Saving loan Details
            log.info("Saving Item and Gold Loan details of Customer NIC:{}",tempCustomer.getNic());
            Loan tempLoan = modelMapper.map(reqBody.getLoanRequest(),Loan.class);
            tempLoan.setLoanNumber(UUID.randomUUID().toString().substring(0, 10));
            tempLoan.setInitialLoanAmount(reqBody.getLoanRequest().getLoanAmount());
            tempLoan.setUpdatedLoanAmount(reqBody.getLoanRequest().getLoanAmount());
            tempLoan.setTotalBalance(reqBody.getLoanRequest().getLoanAmount()+(reqBody.getLoanRequest().getLoanAmount()*reqBody.getLoanRequest().getRate())/100);
            tempLoan.setCurrentPayed(0.0);
            tempLoan.setStartDate(new Date());
            tempLoan.setCustomer(tempCustomer);

            tempLoan = loanRepository.save(tempLoan);
            log.info("Saved Item and Gold Loan details of Customer NIC:{}",tempCustomer.getNic());

            log.info("Saving details in Loan details table of customerNic:{}",tempCustomer.getNic());
            LoanDetail tempLoanDetail = LoanDetail.builder()
                    .amount(tempLoan.getInitialLoanAmount())
                    .rate(tempLoan.getRate())
                    .type(Type.INITIAL_LOAN)
                    .date(new Date())
                    .loan(tempLoan)
                    .build();
            loanDetailRepository.save(tempLoanDetail);
            log.info("Saved details in Loan details table of customerNic:{}",tempCustomer.getNic());

            log.info("Saving Interest details in Interest table of customerNic:{}",tempCustomer.getNic());
            Interest tempInterest = Interest.builder()
                    .amount(tempLoan.getInitialLoanAmount())
                    .interest((tempLoan.getInitialLoanAmount()*tempLoan.getRate())/100)
                    .loan(tempLoan)
                    .created(new Date())
                    .build();
            interestRepository.save(tempInterest);
            log.info("Saved Interest details in Interest table of customerNic:{}",tempCustomer.getNic());
        }catch (Exception exception){
            exception.printStackTrace();
        }



        return "Done";
    }
}
