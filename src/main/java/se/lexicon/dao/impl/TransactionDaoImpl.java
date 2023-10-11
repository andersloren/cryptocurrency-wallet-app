package se.lexicon.dao.impl;

import org.springframework.stereotype.Component;
import se.lexicon.dao.TransactionDao;
import se.lexicon.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionDaoImpl implements TransactionDao {

    private List<Transaction> storage;


    public TransactionDaoImpl() {
        this.storage = new ArrayList<>();
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        storage.add(transaction);
        return null;
    }

    @Override
    public Optional<Transaction> findById(String transactionId) {
        return storage.stream()
                .filter(transaction -> transaction.getId().equals(transactionId))
                .findFirst();
    }

    @Override
    public List<Transaction> findTransactionsByWalletId(String walletId) {
        return storage.stream()
                .filter(transaction -> transaction.getWalletId().equals(walletId))
                .toList();
    }
}
