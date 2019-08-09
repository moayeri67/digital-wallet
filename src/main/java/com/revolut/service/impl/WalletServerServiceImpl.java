package com.revolut.service.impl;

import com.google.inject.Inject;
import com.revolut.dao.intrfce.WalletDao;
import com.revolut.dto.WalletDTO;
import com.revolut.entity.Wallet;
import com.revolut.service.intrfce.WalletServerService;
import com.revolut.validation.WalletValidator;


import java.math.BigDecimal;
import java.util.List;

public class WalletServerServiceImpl implements WalletServerService {
    @Inject
    private WalletValidator walletValidator;

    @Inject
    private WalletDao walletDao;

    public String deposit(WalletDTO walletDTO) throws Exception {
        walletValidator.validateRequest(walletDTO);

        BigDecimal depositAmount = new BigDecimal(walletDTO.getAmount());

        Wallet userWallet = walletDao.getWalletsByUserIdAndCurrency(walletDTO.getFromAccountNumber(), walletDTO.getCurrency());

        if (userWallet == null) {
            throw new Exception("Account number dose not exist !!!");
        }

        userWallet.setBalance(userWallet.getBalance().add(depositAmount));
        walletDao.updateWallet(userWallet);

        return "DEPOSIT_TRANSACTION_SUCCESS_DONE";
    }

    public String withdraw(WalletDTO walletDTO) throws Exception {
        walletValidator.validateRequest(walletDTO);

        BigDecimal withdrawAmount = new BigDecimal(walletDTO.getAmount());

        Wallet userWallet = walletDao.getWalletsByUserIdAndCurrency(walletDTO.getFromAccountNumber(), walletDTO.getCurrency());

        if (userWallet == null) {
            throw new Exception("Account number dose not exist !!!");
        }

        // check if the amount is less than user balance
        if (userWallet.getBalance().compareTo(withdrawAmount) < 0) {
            throw new Exception("INSUFFICIENT_BALANCE");
        }

        userWallet.setBalance(userWallet.getBalance().subtract(withdrawAmount));
        walletDao.updateWallet(userWallet);

        return "WITHDRAW_TRANSACTION_SUCCESS_DONE";
    }

    public String transfer(WalletDTO walletDTO) throws Exception {
        walletValidator.validateRequest(walletDTO);

        BigDecimal transferAmount = new BigDecimal(walletDTO.getAmount());

        // check fromUser wallet existing.
        Wallet fromUserWallet = walletDao.getWalletsByUserIdAndCurrency(walletDTO.getFromAccountNumber(), walletDTO.getCurrency());

        if (fromUserWallet == null) {
            throw new Exception("From account number dose not exist !!!");
        }

        // check toUser wallet existing.
        Wallet toUserWallet = walletDao.getWalletsByUserIdAndCurrency(walletDTO.getToAccountNumber(), walletDTO.getCurrency());

        if (toUserWallet == null) {
            throw new Exception("To account number dose not exist !!!");
        }

        // check if the amount is less than fromUser balance
        if (fromUserWallet.getBalance().compareTo(transferAmount) < 0) {
            throw new Exception("INSUFFICIENT_BALANCE");
        }

        // withdraw fromUser wallet.
        fromUserWallet.setBalance(fromUserWallet.getBalance().subtract(transferAmount));
        walletDao.updateWallet(fromUserWallet);

        // deposit toUser wallet.
        toUserWallet.setBalance(toUserWallet.getBalance().add(transferAmount));
        walletDao.updateWallet(toUserWallet);

        return "TRANSFER_TRANSACTION_SUCCESS_DONE";
    }

    public List<Wallet> balance(int accountNumber) throws Exception {
        List<Wallet> userWallets = walletDao.getWalletsByUserId(accountNumber);

        if (userWallets == null) {
            throw new Exception("Account number dose not exist !!!");
        }

        return userWallets;
    }
}
