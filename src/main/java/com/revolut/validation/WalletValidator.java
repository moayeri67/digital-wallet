package com.revolut.validation;

import com.revolut.dto.WalletDTO;
import spark.utils.StringUtils;

import java.math.BigDecimal;

public class WalletValidator {
    public void validateRequest(WalletDTO walletDTO) throws Exception{
        if (StringUtils.isEmpty(walletDTO.getAmount())) {
            throw new Exception("INVALID_ARGUMENTS");
        }

        try {
            new BigDecimal(walletDTO.getAmount());
        } catch (NumberFormatException numberFormatException) {
            throw new Exception("INVALID_ARGUMENTS");
        }

        if (new BigDecimal(walletDTO.getAmount()).compareTo(BigDecimal.ZERO) < 1) {
            throw new Exception("AMOUNT_CANNOT_LES_THAN_ZERO");
        }

        if (walletDTO.getCurrency() < 0 || walletDTO.getCurrency() > 2) {
            throw new Exception("INVALID_CURRENCY");
        }
    }
}
