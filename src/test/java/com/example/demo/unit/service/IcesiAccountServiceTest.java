package com.example.demo.unit.service;

import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import com.example.demo.mapper.IcesiAccountMapper;
import com.example.demo.mapper.IcesiAccountMapperImpl;
import com.example.demo.repository.IcesiAccountRepository;
import com.example.demo.service.IcesiAccountService;

public class IcesiAccountServiceTest {
    private IcesiAccountService icesiAccountService;

    private IcesiAccountRepository icesiAccountRepository;

    private IcesiAccountMapper icesiAccountMapper;

    @BeforeEach
    private void init() {
        icesiAccountMapper = spy(IcesiAccountMapper.class);
        icesiAccountRepository = mock(IcesiAccountRepository.class);
        icesiAccountService = new IcesiAccountService(icesiAccountRepository, icesiAccountMapper);

    }

    

}
