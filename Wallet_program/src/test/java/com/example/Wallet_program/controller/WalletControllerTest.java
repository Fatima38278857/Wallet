package com.example.Wallet_program.controller;

import com.example.Wallet_program.dto.WalletDto;
import com.example.Wallet_program.model.Wallet;
import com.example.Wallet_program.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WalletService walletService;

    @InjectMocks
    private WalletController walletController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(walletController).build();

    }

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testOperateWallet() throws Exception {
        WalletDto walletDto = new WalletDto();
        walletDto.setAmount(100L);  // Используем Long вместо double

        Wallet wallet = new Wallet();
        wallet.setWalletId(1L);  // Используем setWalletId вместо setId
        wallet.setBalance(100L);  // Используем Long вместо Double

        Mockito.when(walletService.updateBalance(Mockito.any(WalletDto.class))).thenReturn(wallet);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/wallet/operateWallet")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(walletDto))

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.walletId", is(wallet.getWalletId().intValue())))
                .andExpect(jsonPath("$.balance", is(wallet.getBalance().intValue())));
    }

    @Test
    public void testGetBalance() throws Exception {
        Wallet wallet = new Wallet();
        wallet.setWalletId(1L);  // Используем setWalletId вместо setId
        wallet.setBalance(100L);  // Используем Long вместо Double

        Mockito.when(walletService.getBalance(1L)).thenReturn(wallet);

        mockMvc.perform(get("/wallet/{walletId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.walletId", is(wallet.getWalletId().intValue())))
                .andExpect(jsonPath("$.balance", is(wallet.getBalance().intValue())));
    }

}

