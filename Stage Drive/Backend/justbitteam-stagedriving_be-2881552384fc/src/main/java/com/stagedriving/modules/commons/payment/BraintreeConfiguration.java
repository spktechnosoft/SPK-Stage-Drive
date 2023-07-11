package com.stagedriving.modules.commons.payment;

import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author Bo Gotthardt
 */
public class BraintreeConfiguration {
    @NotNull
    private String environment;// = "sandbox";
    @NotNull
    private String merchantId;// = "mhf373frm8mj7kx5";
    @NotNull
    private String publicKey;// = "xygbb6k58t8wpzs2";
    @NotNull
    private String privateKey;// = "b8f07281f517d305011e3175921c36e8";

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
