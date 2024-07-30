package com.example.Wallet_program.controller;

import com.example.Wallet_program.dto.WalletDto;
import com.example.Wallet_program.model.Wallet;
import com.example.Wallet_program.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@Validated
public class WalletController {
    @Autowired
    private WalletService walletService;


    @PostMapping("/operateWallet")
    public ResponseEntity<Wallet> operateWallet(@RequestBody @Validated WalletDto request) {
        Wallet updatedWallet = walletService.updateBalance(request);
        return ResponseEntity.ok(updatedWallet);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<Wallet> getBalance(@PathVariable Long walletId) {
        Wallet wallet = walletService.getBalance(walletId);
        return ResponseEntity.ok(wallet);
    }
}
