package se.lexicon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.dao.TransactionDao;
import se.lexicon.dao.WalletDao;
import se.lexicon.exception.WalletNotFoundException;
import se.lexicon.model.CryptoCurrency;
import se.lexicon.model.Transaction;
import se.lexicon.model.Wallet;
import se.lexicon.service.TransactionManagement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionManagementImpl implements TransactionManagement {

    private WalletDao walletDao;
    private TransactionDao transactionDao;

    @Autowired
    public TransactionManagementImpl(WalletDao walletDao, TransactionDao transactionDao) {
        this.walletDao = walletDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public Transaction createDepositTransaction(String walletId, CryptoCurrency cryptoCurrency, BigDecimal amount, String description) {
        if (walletId == null || cryptoCurrency == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Wallet params were not valid.");
        }
        Optional<Wallet> optionalWallet = walletDao.findWallet(walletId);
        if (optionalWallet.isEmpty()) {
            throw new WalletNotFoundException("Wallet not found.");
        }
        Wallet wallet = optionalWallet.get();
        wallet.deposit(cryptoCurrency, amount);
        Transaction transaction = new Transaction("Deposit", amount, walletId, cryptoCurrency.getName(), description);
        transaction.setDescription(description);
        Transaction createdTransaction = transactionDao.createTransaction(transaction);
        return createdTransaction;
    }

    @Override
    public Transaction createWithdrawTransaction(String walletId, CryptoCurrency cryptoCurrency, BigDecimal amount, String description) {
        // TODO: 11/10/2023 implement later
        return null;
    }

    @Override
    public List<Transaction> getTransactionByWalletId(String walletId) {
        // TODO: 11/10/2023 implement later
        return null;
    }
}
