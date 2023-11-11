package com.ym.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BankAccount {

    @Id
    private String id;
    private LocalDate createdAt;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void copy(BankAccount bankAccount){
        this.setId(bankAccount.id == null ? this.id : bankAccount.id);
        this.setCreatedAt(bankAccount.createdAt == null ? this.createdAt : bankAccount.createdAt);
        this.setBalance(bankAccount.balance == null ? this.balance : bankAccount.balance);
        this.setCurrency(bankAccount.currency == null ? this.currency : bankAccount.currency);
        this.setType(bankAccount.type == null ? this.type : bankAccount.type);
        if(bankAccount.getCustomer().getId() != null)
            this.setCustomer(Customer.builder().id(bankAccount.getCustomer().getId()).build());
    }
}
