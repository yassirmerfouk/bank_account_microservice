package com.ym.service;

import com.ym.dto.BankAccountRequestDto;
import com.ym.dto.BankAccountResponseDto;

import java.util.List;

public interface IBankAccountService {

    BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountDto);
    BankAccountResponseDto updateAccount(String accountId,BankAccountRequestDto bankAccountDto);
    void deleteAccount(String accountId);
    BankAccountResponseDto getAccount(String accountId);
    List<BankAccountResponseDto> getAllAccounts();

    List<BankAccountResponseDto> getPageAccounts(int page, int size);
}
