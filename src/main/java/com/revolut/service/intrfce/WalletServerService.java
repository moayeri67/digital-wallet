package com.revolut.service.intrfce;

import com.revolut.dto.WalletDTO;
import com.revolut.entity.Wallet;

import java.util.List;

public interface WalletServerService {
    String deposit(WalletDTO walletDTO) throws Exception;

    String withdraw(WalletDTO walletDTO) throws Exception;

    String transfer(WalletDTO walletDTO) throws Exception;

    List<Wallet> balance(int userID) throws Exception;
}
