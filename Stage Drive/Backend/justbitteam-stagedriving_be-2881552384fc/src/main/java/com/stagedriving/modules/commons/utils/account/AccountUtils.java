package com.stagedriving.modules.commons.utils.account;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountConnection;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class AccountUtils {

    @Inject
    public AccountUtils() {
    }

    public String extractToken(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String[] parts = authorization.split(" ");
            if (parts.length == 2) {
                if (parts[1] != null && parts[1].equals("null")) {
                    return null;
                }
                return parts[1];
            }
        }

        return null;
    }

    public Account merge(Account oldAccount, Account newAccount) {

        oldAccount.setFirstname(newAccount.getFirstname() != null ? newAccount.getFirstname() : oldAccount.getFirstname());
        oldAccount.setMiddlename(newAccount.getMiddlename() != null ? newAccount.getMiddlename() : oldAccount.getMiddlename());
        oldAccount.setLastname(newAccount.getLastname() != null ? newAccount.getLastname() : oldAccount.getLastname());
        oldAccount.setEmail(newAccount.getEmail() != null ? newAccount.getEmail() : oldAccount.getEmail());
        oldAccount.setTelephone(newAccount.getTelephone() != null ? newAccount.getTelephone() : oldAccount.getTelephone());
        oldAccount.setAddress(newAccount.getAddress() != null ? newAccount.getAddress() : oldAccount.getAddress());
        oldAccount.setCountry(newAccount.getCountry() != null ? newAccount.getCountry()  : oldAccount.getCountry());
        oldAccount.setTown(newAccount.getTown() != null ? newAccount.getTown()  : oldAccount.getTown());
        oldAccount.setCity(newAccount.getCity() != null ? newAccount.getCity()  : oldAccount.getCity());
        oldAccount.setZipcode(newAccount.getZipcode() != null ? newAccount.getZipcode()  : oldAccount.getZipcode());
        oldAccount.setStatus(newAccount.getStatus() != null ? newAccount.getStatus()  : oldAccount.getStatus());
        oldAccount.setGender(newAccount.getGender() != null ? newAccount.getGender()  : oldAccount.getGender());
        oldAccount.setBirthday(newAccount.getBirthday() != null ? newAccount.getBirthday()  : oldAccount.getBirthday());
        oldAccount.setMobile(newAccount.getMobile()  != null ? newAccount.getMobile()  : oldAccount.getMobile());
        oldAccount.setTelephone(newAccount.getTelephone()  != null ? newAccount.getTelephone()  : oldAccount.getTelephone());
        oldAccount.setPec(newAccount.getPec()  != null ? newAccount.getPec()  : oldAccount.getPec());
        oldAccount.setNote(newAccount.getNote()  != null ? newAccount.getNote()  : oldAccount.getNote());
        oldAccount.setPassword(newAccount.getPassword()  != null ? newAccount.getPassword()  : oldAccount.getPassword());
        oldAccount.setRole(newAccount.getRole()  != null ? newAccount.getRole()  : oldAccount.getRole());
        oldAccount.setUsername(newAccount.getUsername()  != null ? newAccount.getUsername()  : oldAccount.getUsername());
        oldAccount.setFavStyle(newAccount.getFavStyle()  != null ? newAccount.getFavStyle()  : oldAccount.getFavStyle());
        oldAccount.setFavCategory(newAccount.getFavCategory()  != null ? newAccount.getFavCategory()  : oldAccount.getFavCategory());
        oldAccount.setCompanyAddress(newAccount.getCompanyAddress()  != null ? newAccount.getCompanyAddress()  : oldAccount.getCompanyAddress());
        oldAccount.setCompanyName(newAccount.getCompanyName()  != null ? newAccount.getCompanyName()  : oldAccount.getCompanyName());
        oldAccount.setCompanyRef(newAccount.getCompanyRef()  != null ? newAccount.getCompanyRef()  : oldAccount.getCompanyRef());
        oldAccount.setCompanyCity(newAccount.getCompanyCity()  != null ? newAccount.getCompanyCity()  : oldAccount.getCompanyCity());
        oldAccount.setCompanyCountry(newAccount.getCompanyCountry()  != null ? newAccount.getCompanyCountry()  : oldAccount.getCompanyCountry());
        oldAccount.setCompanyVatId(newAccount.getCompanyVatId()  != null ? newAccount.getCompanyVatId()  : oldAccount.getCompanyVatId());
        oldAccount.setCompanyZipcode(newAccount.getCompanyZipcode()  != null ? newAccount.getCompanyZipcode()  : oldAccount.getCompanyZipcode());

        return oldAccount;
    }

    public AccountConnection merge(AccountConnection oldAccountConnection, AccountConnection newAccountConnection) {

        oldAccountConnection.setId(newAccountConnection.getId());
        oldAccountConnection.setProvider(newAccountConnection.getProvider() != null ? newAccountConnection.getProvider() : oldAccountConnection.getProvider());
        oldAccountConnection.setToken(newAccountConnection.getToken() != null ? newAccountConnection.getToken() : oldAccountConnection.getToken());
        oldAccountConnection.setExpires(newAccountConnection.getExpires() != null ? newAccountConnection.getExpires() : oldAccountConnection.getExpires());
        oldAccountConnection.setRefresh(newAccountConnection.getRefresh() != null ? newAccountConnection.getRefresh() : oldAccountConnection.getRefresh());
        oldAccountConnection.setCode(newAccountConnection.getCode() != null ? newAccountConnection.getCode() : oldAccountConnection.getCode());

        return oldAccountConnection;
    }
}


