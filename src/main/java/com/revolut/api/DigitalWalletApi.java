package com.revolut.api;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.revolut.dto.WalletDTO;
import com.revolut.entity.Wallet;
import com.revolut.service.intrfce.WalletServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.servlet.SparkApplication;

import java.util.List;

import static spark.Spark.*;

public class DigitalWalletApi implements SparkApplication {
    private static final Logger logger = LoggerFactory.getLogger(DigitalWalletApi.class);

    @Inject
    private WalletServerService walletServerService;

    public void init() {
        // Start embedded server at this port
        port(8080);

        post("/deposit", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                try {
                    logger.info(this.getClass().getName() + ".deposit, Request Received. " + request.toString());

                    String depositResult = walletServerService.deposit(new WalletDTO(Integer.valueOf(request.queryParams("accountNumber")), Integer.valueOf(request.queryParams("currency")), request.queryParams("amount")));

                    logger.info("Deposit Wallet Updated SuccessFully");

                    return new Gson().toJson(depositResult);
                } catch (Exception e) {
                    logger.error("Exception :", this.getClass().getName() + ".deposit, " + e);

                    return new Gson().toJson("Exception : " + e.getMessage());
                }
            }
        });

        post("/withdraw", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                try{
                    logger.info(this.getClass().getName() + ".withdraw, Request Received. " + request.toString());

                    String withdrawResult = walletServerService.withdraw(new WalletDTO(Integer.valueOf(request.queryParams("accountNumber")), Integer.valueOf(request.queryParams("currency")), request.queryParams("amount")));

                    logger.info("Withdraw Wallet Updated SuccessFully");

                    return new Gson().toJson(withdrawResult);
                } catch (Exception e) {
                    logger.error("Exception :", this.getClass().getName() + ".withdraw, " + e);

                    return new Gson().toJson("Exception : " + e.getMessage());
                }
            }
        });

        post("/transfer", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                try{
                    logger.info(this.getClass().getName() + ".transfer, Request Received. " + request.toString());

                    String withdrawResult = walletServerService.transfer(new WalletDTO(Integer.valueOf(request.queryParams("fromAccountNumber")), Integer.valueOf(request.queryParams("toAccountNumber")), Integer.valueOf(request.queryParams("currency")), request.queryParams("amount")));

                    logger.info("transfer transaction done SuccessFully");

                    return new Gson().toJson(withdrawResult);
                } catch (Exception e) {
                    logger.error("Exception :", this.getClass().getName() + ".transfer, " + e);

                    return new Gson().toJson("Exception : " + e.getMessage());
                }
            }
        });

        post("/balance", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                try{
                    logger.info(this.getClass().getName() + ".balance, Request Received. " + request.toString());

                    List<Wallet> walletList = walletServerService.balance(Integer.valueOf(request.queryParams("accountNumber")));

                    logger.info("Balance Transaction Done SuccessFully");

                    return new Gson().toJson(walletList);
                } catch (Exception e) {
                    logger.error("Exception :", this.getClass().getName() + ".balance, " + e);
                    return new Gson().toJson("Exception : " + e.getMessage());
                }
            }
        });
    }
}