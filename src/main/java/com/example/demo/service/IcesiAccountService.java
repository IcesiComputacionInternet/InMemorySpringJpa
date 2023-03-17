package com.example.demo.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.IcesiAccountCreateDTO;
import com.example.demo.mapper.IcesiAccountMapper;
import com.example.demo.model.IcesiAccount;
import com.example.demo.repository.IcesiAccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IcesiAccountService {
    
    private final IcesiAccountRepository icesiAccountRepository;

    private final IcesiAccountMapper IcesiAccountMapper;

    public IcesiAccount create(IcesiAccountCreateDTO account) {
        if(account.getBalance() < 0) {
            throw new RuntimeException("The account balance cannot be negative");
        }
        IcesiAccount icesiAccount = IcesiAccountMapper.fromIcesiAccountDTO(account);
        icesiAccount.setAccountId(UUID.randomUUID());
        icesiAccount.setAccountNumber(generateAccountNumber());
        if(icesiAccountRepository.findByAccountNumber(icesiAccount.getAccountNumber()).isPresent()){
            throw new RuntimeException("This account number is already in use");
        }
        return icesiAccountRepository.save(icesiAccount);
    }

    public String generateAccountNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
    
        // Generate the first 3 digits
        for (int i = 0; i < 3; i++) {
            int generatedRandom = random.nextInt(10); // Generate a random digit between 0 and 9
            stringBuilder.append(generatedRandom);
        }
    
        stringBuilder.append("-");
    
        // Generate the next 6 digits
        for (int i = 0; i < 6; i++) {
            int generatedRandom = random.nextInt(10); // Generate a random digit between 0 and 9
            stringBuilder.append(generatedRandom);
        }
    
        stringBuilder.append("-");
    
        // Generate the last 2 digits
        for (int i = 0; i < 2; i++) {
            int generatedRandom = random.nextInt(10); // Generate a random digit between 0 and 9
            stringBuilder.append(generatedRandom);
        }
    
        return stringBuilder.toString();
    }

    public void enableAccount(IcesiAccountCreateDTO account) {
        account.setActive(true);
    }

    public void disableAccount(IcesiAccountCreateDTO account) {
        if(account.getBalance() == 0) {
            account.setActive(false);
        }
        else {
            throw new RuntimeException("This accout cannot be disabled, its balances is not 0");
        }
    }

    public void withdrawalMoney(long amountToWithdraw, IcesiAccountCreateDTO account) {
        if((account.getBalance() - amountToWithdraw) < 0) {
            throw new RuntimeException("This account does not have enough funds");
        }
        else {
            account.setBalance(account.getBalance()- amountToWithdraw);
        }
    }

    public void depositMoney(long amountToDeposit, IcesiAccountCreateDTO account) {
        account.setBalance(account.getBalance() + amountToDeposit);
    }

    public void transferMoneyToAnotherAccount(long amountToTransfer, IcesiAccountCreateDTO originAccount, IcesiAccountCreateDTO destinationAccount) {
        if(originAccount.getType().equals("deposit")) {
            throw new RuntimeException("The origin account is not allowed to be transfer money");
        }
        else {
            if(destinationAccount.getType().equals("deposit")) {
                throw new RuntimeException("The destination account is not allowed to be transferred money");
            }
            else {
                if((originAccount.getBalance() - amountToTransfer) < 0) {
                    throw new RuntimeException("The origin account does not have enough funds");
                }
                else {
                    originAccount.setBalance(originAccount.getBalance() - amountToTransfer);
                    destinationAccount.setBalance(destinationAccount.getBalance() + amountToTransfer);
                }
            }
        }
    }
}
