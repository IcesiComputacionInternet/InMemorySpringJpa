package com.example.demo.service;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.IcesiUserCreateDTO;
import com.example.demo.mapper.IcesiUserMapper;
import com.example.demo.model.IcesiUser;
import com.example.demo.repository.IcesiUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class IcesiUserService {

    private final IcesiUserRepository icesiUserRepository;

    private final IcesiUserMapper icesiUserMapper;

    public  IcesiUser create(IcesiUserCreateDTO user) {
        if(icesiUserRepository.findByEmail(user.getEmail()).isPresent() &&
        icesiUserRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("These email and phone number are already in use");

        }
        else if(icesiUserRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("This email is already in use");
        }
        else if(icesiUserRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("This phone number is already in use");
        }
        IcesiUser icesiUser = icesiUserMapper.fromIcesiUserDTO(user);
        icesiUser.setUserId(UUID.randomUUID());
        return icesiUserRepository.save(icesiUser);
    }

}
