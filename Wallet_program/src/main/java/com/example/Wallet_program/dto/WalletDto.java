package com.example.Wallet_program.dto;

import com.example.Wallet_program.enumm.OperationType;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;


@Data
public class WalletDto {

    @NotNull
    private Long walletId;
    @NotNull
    private OperationType operationType;
    @NotNull
    private Long amount;
}

