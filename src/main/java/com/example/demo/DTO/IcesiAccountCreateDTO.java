package com.example.demo.DTO;

import lombok.Data;

@Data
public class IcesiAccountCreateDTO {
    private String accountNumber;

    private long balance;

    private String type;

    private boolean active;
}
