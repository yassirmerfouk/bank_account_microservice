package com.ym.dto;

import com.ym.model.AccountType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccountResponseDto {
    private String id;
    private LocalDate createdAt;
    private Double balance;
    private String currency;
    private AccountType type;
}
