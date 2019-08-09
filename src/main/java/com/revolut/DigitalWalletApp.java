package com.revolut;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.revolut.api.DigitalWalletApi;
import com.revolut.dao.intrfce.WalletDao;
import com.revolut.module.AppModule;

public class DigitalWalletApp {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new AppModule());

        WalletDao walletDao = injector.getInstance(WalletDao.class);
        walletDao.initialRepository();

        DigitalWalletApi digitalWalletApi = injector.getInstance(DigitalWalletApi.class);
        digitalWalletApi.init();
    }
}