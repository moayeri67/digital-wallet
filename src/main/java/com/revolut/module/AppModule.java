package com.revolut.module;

import com.google.inject.AbstractModule;
import com.revolut.dao.impl.WalletDaoImpl;
import com.revolut.dao.intrfce.WalletDao;
import com.revolut.service.impl.WalletServerServiceImpl;
import com.revolut.service.intrfce.WalletServerService;

public class AppModule extends AbstractModule {
    protected void configure() {
        bind(WalletServerService.class).to(WalletServerServiceImpl.class);
        bind(WalletDao.class).to(WalletDaoImpl.class);
    }
}