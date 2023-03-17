package com.example.demo.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.DTO.IcesiUserCreateDTO;
import com.example.demo.mapper.IcesiUserMapper;
import com.example.demo.mapper.IcesiUserMapperImpl;
import com.example.demo.model.IcesiUser;
import com.example.demo.repository.IcesiUserRepository;
import com.example.demo.service.IcesiUserService;

public class IcesiUserServiceTest {
    private IcesiUserService icesiUserService;

    private IcesiUserRepository icesiUserRepository;

    private IcesiUserMapper icesiUserMapper;

    @BeforeEach
    private void init() {
        icesiUserMapper = spy(IcesiUserMapperImpl.class);
        icesiUserRepository = mock(IcesiUserRepository.class);
        icesiUserService =  new IcesiUserService(icesiUserRepository, icesiUserMapper);
    }

    @Test
    public void testCreateIcesiUser() {
        icesiUserService.create(defaultIcesiUserCreateDTO());
        IcesiUser icesiUser1 = IcesiUser.builder()
            .firstName("John")
            .lastName("Doe")
            .email("testEmail@example.com")
            .phoneNumber("999999")
            .password("1234")
            .build();
        verify(icesiUserRepository, times(1)).save(argThat(new IcesiUserMatcher(icesiUser1)));


    }

    private IcesiUserCreateDTO defaultIcesiUserCreateDTO() {
        return IcesiUserCreateDTO.builder()
            .firstName("John")
            .lastName("Doe")
            .email("testEmail@example.com")
            .phoneNumber("999999")
            .password("1234")
            .build();
    }

    @Test
    public void testCreateUserWhenEmailAlreadyExists() {
        when(icesiUserRepository.findByEmail(any())).thenReturn(Optional.of(defaultIcesiUser()));
        try {
            icesiUserService.create(defaultIcesiUserCreateDTO());
            fail();
        } catch(RuntimeException exception) {
            String message = exception.getMessage();
            assertEquals("This email is already in use", message);
        }

    }


    @Test
    public void testCreateUserWhenPhoneNumberAlreadyExists() {
        when(icesiUserRepository.findByPhoneNumber(any())).thenReturn(Optional.of(defaultIcesiUser()));
        try {
            icesiUserService.create(defaultIcesiUserCreateDTO());
            fail();
        } catch(RuntimeException exception) {
            String message = exception.getMessage();
            assertEquals("This phone number is already in use", message);
        }

    }

    @Test
    public void testCreateUserWhenEmailAndPhoneNumberAlreadyExists() {
        when(icesiUserRepository.findByEmail(any())).thenReturn(Optional.of(defaultIcesiUser()));
        when(icesiUserRepository.findByPhoneNumber(any())).thenReturn(Optional.of(defaultIcesiUser()));
     
        try {
            icesiUserService.create(defaultIcesiUserCreateDTO());
            fail();
        } catch(RuntimeException exception) {
            String message = exception.getMessage();
            assertEquals("These email and phone number are already in use", message);
        }

    }

    private IcesiUser defaultIcesiUser() {
        return IcesiUser.builder()
            .firstName("John")
            .lastName("Doe")
            .email("testEmail@example.com")
            .phoneNumber("999999")
            .password("1234")
            .build();
    }

}
