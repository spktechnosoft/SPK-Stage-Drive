package com.stagedriving.modules.commons.utils.billing;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.entities.AccountBilling;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class BillingUtils {

    @Inject
    public BillingUtils() {
    }
    
    public AccountBilling merge(AccountBilling oldAccountBilling, AccountBilling newAccountBilling) {

        oldAccountBilling.setIban(newAccountBilling.getIban() != null ? newAccountBilling.getIban() : oldAccountBilling.getIban());
        oldAccountBilling.setProvider(newAccountBilling.getProvider() != null ? newAccountBilling.getProvider() : oldAccountBilling.getProvider());

        return oldAccountBilling;
    }
}


