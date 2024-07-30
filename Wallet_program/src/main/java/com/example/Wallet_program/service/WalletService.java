package com.example.Wallet_program.service;

import com.example.Wallet_program.dto.WalletDto;
import com.example.Wallet_program.enumm.OperationType;
import com.example.Wallet_program.exception.InsufficientFundsException;
import com.example.Wallet_program.exception.WalletNotFoundException;
import com.example.Wallet_program.model.Wallet;
import com.example.Wallet_program.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    public Wallet updateBalance(WalletDto request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        synchronized (wallet.getWalletId().toString().intern()) {
            if (request.getOperationType() == OperationType.DEPOSIT) {
                wallet.setBalance(wallet.getBalance() + request.getAmount());
            } else if (request.getOperationType() == OperationType.WITHDRAW) {
                if (wallet.getBalance() < request.getAmount()) {
                    throw new InsufficientFundsException("Insufficient funds");
                }
                wallet.setBalance(wallet.getBalance() - request.getAmount());
            }
            return walletRepository.save(wallet);
        }
    }

    public Wallet getBalance(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
    }
}
