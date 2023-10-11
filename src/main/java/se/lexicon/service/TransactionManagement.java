package se.lexicon.service;

import se.lexicon.model.CryptoCurrency;
import se.lexicon.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionManagement {

    Transaction createDepositTransaction(String walletId, CryptoCurrency cryptoCurrency, BigDecimal amount, String description);
    Transaction createWithdrawTransaction(String walletId, CryptoCurrency cryptoCurrency, BigDecimal amount, String description);

    List<Transaction> getTransactionByWalletId(String walletId);
}
