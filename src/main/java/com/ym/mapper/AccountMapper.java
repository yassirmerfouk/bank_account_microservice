package com.ym.mapper;

import com.ym.dto.BankAccountRequestDto;
import com.ym.dto.BankAccountResponseDto;
import com.ym.model.BankAccount;
import com.ym.model.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public BankAccount toBankAccount(BankAccountRequestDto bankAccountRequestDto){
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDto, bankAccount);
        bankAccount.setCustomer(Customer.builder().id(bankAccountRequestDto.getCustomerId()).build());
        return bankAccount;
    }

    public BankAccountResponseDto toBankAccountResponseDto(BankAccount bankAccount){
        BankAccountResponseDto bankAccountResponseDto = new BankAccountResponseDto();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDto);
        return bankAccountResponseDto;
    }
}
