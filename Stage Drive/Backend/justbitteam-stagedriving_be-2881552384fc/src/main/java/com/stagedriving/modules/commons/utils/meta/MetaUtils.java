package com.stagedriving.modules.commons.utils.meta;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.entities.AccountMeta;

public class MetaUtils {

    @Inject
    public MetaUtils() {
    }

    public AccountMeta merge(AccountMeta oldAccountMeta, AccountMeta newAccountMeta) {

        oldAccountMeta.setMwrench(newAccountMeta.getMwrench() != null ? newAccountMeta.getMwrench() : oldAccountMeta.getMwrench());
        oldAccountMeta.setMvalue(newAccountMeta.getMvalue() != null ? newAccountMeta.getMvalue() : oldAccountMeta.getMvalue());

        return oldAccountMeta;
    }
}
