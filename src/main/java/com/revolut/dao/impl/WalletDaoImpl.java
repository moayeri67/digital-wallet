package com.revolut.dao.impl;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.revolut.dao.intrfce.WalletDao;
import com.revolut.entity.Wallet;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class WalletDaoImpl implements WalletDao {
    private SqlMapClient sqlmapClient;

    public WalletDaoImpl() {
        try {
            Reader reader = Resources.getResourceAsReader("sql-maps-config.xml");
            sqlmapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int initialRepository() throws Exception {
        return sqlmapClient.update("wallet.initialRepository");
    }

    public Wallet getWalletsByUserIdAndCurrency(int fromAccountNumber, int currency) throws Exception {
        return (Wallet) sqlmapClient.queryForObject("wallet.getWalletsByUserIdAndCurrency", new Wallet(fromAccountNumber, currency));
    }

    public List<Wallet> getWalletsByUserId(int fromAccountNumber) throws Exception{
        return (List<Wallet>) sqlmapClient.queryForList("wallet.getWalletsByUserId", fromAccountNumber);
    }

    public void updateWallet(Wallet wallet) throws Exception {
        sqlmapClient.update("wallet.updateWallet", wallet);
    }
}
