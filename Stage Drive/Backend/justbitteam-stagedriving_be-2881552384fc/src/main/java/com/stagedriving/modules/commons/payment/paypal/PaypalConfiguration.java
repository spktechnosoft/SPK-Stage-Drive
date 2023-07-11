package com.stagedriving.modules.commons.payment.paypal;

import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author Bo Gotthardt
 */
public class PaypalConfiguration {
    @NotNull
    private String environment = "sandbox";
    @NotNull
    private String account = "sb-pclul2248745@business.example.com";
    @NotNull
    private String clientId = "AXDJ7k8wUHp4jl2dglD2NMe8cA-TpvRtyGa8K-JY9Otm8TmCUAKdzYwZLukqCiGF8cogg7RFkfNbS_rC";
    @NotNull
    private String clientSecret = "EMK-XWopoVolDKQYJ6_X7H9rYFch4lfYD4NdoVM5i_rq0srsjVi397jq3rgptkd6QAq-kf3SwzZtXnu7";

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
