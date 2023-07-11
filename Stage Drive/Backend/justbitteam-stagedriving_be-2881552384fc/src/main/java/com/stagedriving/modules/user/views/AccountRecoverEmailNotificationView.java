package com.stagedriving.modules.user.views;

import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.notification.views.EmailNotificationView;
import lombok.Data;

/**
 * Created by simone on 13/10/14.
 */
@Data
public class AccountRecoverEmailNotificationView extends EmailNotificationView {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AccountRecoverEmailNotificationView.class);

    private String password;
    private Account user;

    public AccountRecoverEmailNotificationView() {
        super("AccountRecoverEmailNotificationView");


    }
}
