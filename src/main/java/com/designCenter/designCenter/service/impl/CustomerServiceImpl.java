package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class CustomerServiceImpl implements CustomerService {
}
