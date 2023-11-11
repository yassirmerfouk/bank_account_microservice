package com.ym;

import com.ym.dto.BankAccountRequestDto;
import com.ym.model.AccountType;
import com.ym.model.BankAccount;
import com.ym.model.Customer;
import com.ym.repository.BankAccountRepository;
import com.ym.repository.CustomerRepository;
import com.ym.service.IBankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class BankAccountMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountMicroserviceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(IBankAccountService bankAccountService,
                                               CustomerRepository customerRepository,
                                               BankAccountRepository bankAccountRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Arrays.asList("Yassir Merfouk", "YM Merfouk").forEach(
                        x -> {
                            Customer customer = Customer.builder()
                                    .fullName(x)
                                    .build();
                            customerRepository.save(customer);
                            for(int i=0;i<10;i++){
/*                                BankAccountRequestDto bankAccountRequestDto =
                                        BankAccountRequestDto.builder()
                                                .balance(10000 + Math.random() * 90000)
                                                .type(Math.random() < 0.5 ? AccountType.SAVING_ACCOUNT : AccountType.CURRENT_ACCOUNT)
                                                .currency(Math.random() < 0.5 ? "EUR" : "USD")
                                                .build();
                                bankAccountService.addAccount(bankAccountRequestDto);*/
                                BankAccount bankAccount = BankAccount.builder()
                                        .id(UUID.randomUUID().toString())
                                        .createdAt(LocalDate.now())
                                        .balance(10000 + Math.random() * 90000)
                                        .currency(Math.random() < 0.5 ? "EUR" : "USD")
                                        .type(Math.random() < 0.5 ? AccountType.SAVING_ACCOUNT : AccountType.CURRENT_ACCOUNT)
                                        .customer(customer)
                                        .build();
                                bankAccountRepository.save(bankAccount);
                            }
                        }
                );
            }
        };
    }
}
