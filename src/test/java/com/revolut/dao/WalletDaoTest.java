package com.revolut.dao;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.revolut.dao.intrfce.WalletDao;
import com.revolut.dto.WalletDTO;
import com.revolut.entity.Wallet;
import com.revolut.module.AppModule;
import com.revolut.service.intrfce.WalletServerService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WalletDaoTest {

    private static WalletDao walletDao;

    private static WalletServerService walletServerService;

    @BeforeClass
    public static void setUp() throws Exception {
        Injector injector = Guice.createInjector(new AppModule());

        walletDao = injector.getInstance(WalletDao.class);
        walletDao.initialRepository();

        walletServerService = injector.getInstance(WalletServerService.class);
    }

    @Test
    public void testRequestedServerScenario() throws Exception {
        // Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
        try {
            walletServerService.withdraw(new WalletDTO(100, 0, "200"));
        }
        catch (Exception exp){
            System.out.println("Step 1, withdraw Exception : " + exp.getMessage());
        }

        // Make a deposit of USD 100 to user with id 1.
        try {
            System.out.println("Step 2, deposit: " + walletServerService.deposit(new WalletDTO(100, 0, "100")));
        }catch (Exception exp){
            System.out.println("Step 2, deposit Exception : " + exp.getMessage());
        }

        // Check that all balances are correct
        try {
            System.out.println("Step 3, balance : " + walletServerService.balance(100));
        }catch (Exception exp){
            System.out.println("Step 3, balance Exception : " + exp.getMessage());
        }

        // Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
        try {
            walletServerService.withdraw(new WalletDTO(100, 0, "200"));
        }catch (Exception exp){
            System.out.println("Step 4, withdraw Exception : " + exp.getMessage());
        }

        // Make a deposit of EUR 100 to user with id 1.
        try {
            System.out.println("Step 5, deposit: " + walletServerService.deposit(new WalletDTO(100, 1, "100")));
        }catch (Exception exp){
            System.out.println("Step 5, deposit Exception : " + exp.getMessage());
        }

        // Check that all balances are correct
        try {
            System.out.println("Step 6, balance : " + walletServerService.balance(100));
        }catch (Exception exp){
            System.out.println("Step 6, balance Exception : " + exp.getMessage());
        }

        // Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
        try {
            walletServerService.withdraw(new WalletDTO(100, 0, "200"));
        }catch (Exception exp){
            System.out.println("Step 7, withdraw Exception : " + exp.getMessage());
        }

        // Make a deposit of USD 100 to user with id 1.
        try {
            System.out.println("Step 8, deposit: " + walletServerService.deposit(new WalletDTO(100, 0, "100")));
        }catch (Exception exp){
            System.out.println("Step 8, deposit Exception : " + exp.getMessage());
        }

        // Check that all balances are correct
        try {
            System.out.println("Step 9, balance : " + walletServerService.balance(100));
        }catch (Exception exp){
            System.out.println("Step 9, balance Exception : " + exp.getMessage());
        }

        // Make a withdrawal of USD 200 for user with id 1. Must return "ok".
        try {
            System.out.println("Step 10, withdraw : " + walletServerService.withdraw(new WalletDTO(100, 0, "200")));
        }catch (Exception exp){
            System.out.println("Step 10, withdraw Exception : " + exp.getMessage());
        }

        // Check that all balances are correct.
        try {
            System.out.println("Step 11, balance : " + walletServerService.balance(100));
        }catch (Exception exp){
            System.out.println("Step 11, balance Exception : " + exp.getMessage());
        }

        // Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
        try {
            walletServerService.withdraw(new WalletDTO(100, 0, "200"));
        }catch (Exception exp){
            System.out.println("Step 12, withdraw Exception : " + exp.getMessage());
        }
    }

    @Test
    public void testGetWalletsByUserId() throws Exception {
        List<Wallet> wallets = walletDao.getWalletsByUserId(100);
        assertThat(wallets).hasSize(2);
    }

    @Test
    public void testGetWalletsByUserIdAndCurrency() throws Exception {
        Wallet wallets = walletDao.getWalletsByUserIdAndCurrency(100, 1);
        assertThat(wallets).isNotNull();
    }
}