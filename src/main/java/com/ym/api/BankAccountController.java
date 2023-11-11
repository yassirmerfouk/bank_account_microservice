package com.ym.api;

import com.ym.dto.BankAccountRequestDto;
import com.ym.dto.BankAccountResponseDto;
import com.ym.service.IBankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class BankAccountController {

    private IBankAccountService bankAccountService;

    @PostMapping
    public BankAccountResponseDto addAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.addAccount(bankAccountRequestDto);
    }

    @PutMapping("/{accountId}")
    public BankAccountResponseDto updateAccount(@PathVariable String accountId, @RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.updateAccount(accountId, bankAccountRequestDto);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable String accountId) {
        bankAccountService.deleteAccount(accountId);
    }

    @GetMapping("/{accountId}")
    public BankAccountResponseDto getAccount(@PathVariable String accountId) {
        return bankAccountService.getAccount(accountId);
    }

    @GetMapping
    public List<BankAccountResponseDto> getAccounts() {
        return bankAccountService.getAllAccounts();
    }
}
