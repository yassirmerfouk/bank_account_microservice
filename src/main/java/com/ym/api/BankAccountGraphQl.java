package com.ym.api;

import com.ym.dto.BankAccountRequestDto;
import com.ym.dto.BankAccountResponseDto;
import com.ym.model.BankAccount;
import com.ym.repository.BankAccountRepository;
import com.ym.service.IBankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class BankAccountGraphQl {

    private BankAccountRepository bankAccountRepository;

    /*private IBankAccountService bankAccountService;

    @QueryMapping
    public List<BankAccountResponseDto> getAccounts(){
        return bankAccountService.getAllAccounts();
    }

    @QueryMapping
    public  BankAccountResponseDto getAccount(@Argument String accountId){
        return bankAccountService.getAccount(accountId);
    }

    @MutationMapping
    public BankAccountResponseDto addAccount(@Argument BankAccountRequestDto bankAccountRequestDto){
        return bankAccountService.addAccount(bankAccountRequestDto);
    }

    @MutationMapping
    public BankAccountResponseDto updateAccount(
            @Argument String accountId,
            @Argument BankAccountRequestDto bankAccountRequestDto
            ){
        return bankAccountService.updateAccount(accountId, bankAccountRequestDto);
    }

    @MutationMapping
    public void deleteAccount(@Argument String accountId){
        bankAccountService.deleteAccount(accountId);
    }*/

    @QueryMapping
    public List<BankAccount> getAccounts(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount getAccount(@Argument String accountId){
        return bankAccountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
    }

    @MutationMapping
    public BankAccount addAccount(@Argument BankAccount bankAccount){
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(LocalDate.now());
        return bankAccountRepository.save(bankAccount);
    }

    @MutationMapping
    public BankAccount updateAccount(@Argument String accountId,@Argument BankAccount bankAccount){
        BankAccount oldBankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        oldBankAccount.copy(bankAccount);
        return bankAccountRepository.save(oldBankAccount);
    }

    @MutationMapping
    public void deleteAccount(@Argument String accountId){
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        bankAccountRepository.delete(bankAccount);
    }

}
