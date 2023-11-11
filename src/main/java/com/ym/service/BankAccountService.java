package com.ym.service;

import com.ym.dto.BankAccountRequestDto;
import com.ym.dto.BankAccountResponseDto;
import com.ym.mapper.AccountMapper;
import com.ym.model.BankAccount;
import com.ym.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankAccountService implements IBankAccountService{

    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountRequestDto) {
        BankAccount bankAccount = accountMapper.toBankAccount(bankAccountRequestDto);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(LocalDate.now());
        bankAccountRepository.save(bankAccount);
        return accountMapper.toBankAccountResponseDto(bankAccount);
    }

    @Override
    public BankAccountResponseDto updateAccount(String accountId, BankAccountRequestDto bankAccountRequestDto) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        bankAccount.copy(accountMapper.toBankAccount(bankAccountRequestDto));
        bankAccountRepository.save(bankAccount);
        return accountMapper.toBankAccountResponseDto(bankAccount);
    }

    @Override
    public void deleteAccount(String accountId) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        bankAccountRepository.delete(bankAccount);
    }

    @Override
    public BankAccountResponseDto getAccount(String accountId) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        return  accountMapper.toBankAccountResponseDto(bankAccount);
    }

    @Override
    public List<BankAccountResponseDto> getAllAccounts() {
        return bankAccountRepository.findAll().stream().map(
                x -> accountMapper.toBankAccountResponseDto(x)
        ).collect(Collectors.toList());
    }

    @Override
    public List<BankAccountResponseDto> getPageAccounts(int page, int size){
        return bankAccountRepository.findAll(PageRequest.of(page, size))
                .stream().map(
                        x -> accountMapper.toBankAccountResponseDto(x)
                ).collect(Collectors.toList());
    }
}
