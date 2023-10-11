package se.lexicon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.dao.WalletDao;
import se.lexicon.exception.WalletNotFoundException;
import se.lexicon.exception.WalletValidationException;
import se.lexicon.model.Wallet;
import se.lexicon.service.WalletManagement;

import java.util.Optional;

@Component
public class WalletManagementImpl implements WalletManagement {

    private WalletDao walletDao;

    @Autowired
    public WalletManagementImpl(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    @Override
    public Wallet create(String walletName) {
        // check wallet from other services
        // ...
        if (walletName == null) {
            throw new WalletValidationException("Param is not valid.", "Wallet Name"); // TODO: 11/10/2023 what is this? 
        }
        // TODO: 11/10/2023 add more logic: if the wallet name exist or no....
        Wallet wallet = new Wallet(walletName);
        return walletDao.createWallet(wallet);
    }

    @Override
    public Wallet getById(String id) {
        Optional<Wallet> optionalWallet = walletDao.findWallet(id);
        if (optionalWallet.isEmpty()) {
            throw new WalletNotFoundException("Wallet not found.");
        }
        return optionalWallet.get();
    }
}
