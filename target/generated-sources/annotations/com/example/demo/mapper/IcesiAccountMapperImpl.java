package com.example.demo.mapper;

import com.example.demo.DTO.IcesiAccountCreateDTO;
import com.example.demo.model.IcesiAccount;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T10:22:07-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@Component
public class IcesiAccountMapperImpl implements IcesiAccountMapper {

    @Override
    public IcesiAccount fromIcesiAccountDTO(IcesiAccountCreateDTO IcesiAccountCreateDTO) {
        if ( IcesiAccountCreateDTO == null ) {
            return null;
        }

        IcesiAccount icesiAccount = new IcesiAccount();

        icesiAccount.setAccountNumber( IcesiAccountCreateDTO.getAccountNumber() );
        icesiAccount.setActive( IcesiAccountCreateDTO.isActive() );
        icesiAccount.setBalance( IcesiAccountCreateDTO.getBalance() );
        icesiAccount.setType( IcesiAccountCreateDTO.getType() );

        return icesiAccount;
    }

    @Override
    public IcesiAccountCreateDTO fromIcesiAccount(IcesiAccount icesiAccount) {
        if ( icesiAccount == null ) {
            return null;
        }

        IcesiAccountCreateDTO icesiAccountCreateDTO = new IcesiAccountCreateDTO();

        icesiAccountCreateDTO.setAccountNumber( icesiAccount.getAccountNumber() );
        icesiAccountCreateDTO.setActive( icesiAccount.isActive() );
        icesiAccountCreateDTO.setBalance( icesiAccount.getBalance() );
        icesiAccountCreateDTO.setType( icesiAccount.getType() );

        return icesiAccountCreateDTO;
    }
}
