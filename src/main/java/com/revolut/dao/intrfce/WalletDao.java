package com.revolut.dao.intrfce;

import com.revolut.entity.Wallet;

import java.util.List;

public interface WalletDao {
    int initialRepository() throws Exception;

    Wallet getWalletsByUserIdAndCurrency(int fromAccountNumber, int currency) throws Exception;

    List<Wallet> getWalletsByUserId(int fromAccountNumber) throws Exception;

    void updateWallet(Wallet wallet) throws Exception;
}
