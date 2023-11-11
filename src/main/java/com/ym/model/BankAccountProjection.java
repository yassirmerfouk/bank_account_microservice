package com.ym.model;

import com.ym.model.AccountType;
import com.ym.model.BankAccount;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "ba_p1",
        types = {BankAccount.class}
)
public interface BankAccountProjection {

    Double getBalance();
    String getCurrency();
    AccountType getType();
}
