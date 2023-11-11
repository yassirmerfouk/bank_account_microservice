package com.ym.dto;

import com.ym.model.AccountType;
import lombok.*;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class BankAccountRequestDto {
    private Double balance;
    private String currency;
    private AccountType type;

    private Long customerId;
}
