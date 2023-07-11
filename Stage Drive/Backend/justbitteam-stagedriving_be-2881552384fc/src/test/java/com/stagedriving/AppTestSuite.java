package com.stagedriving;

import com.stagedriving.modules.commons.conf.AppConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Unit test for simple App.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        /*-----------------------------
            TRUNCATE
        -----------------------------*/
//        TruncateDatabaseRestTest.class,

        /*-----------------------------
            GROUP
        -----------------------------*/
//        GroupResourceRestTest.class,

        /*-----------------------------
            ACCOUNT
        -----------------------------*/
//        AccountResourceRestTest.class,
//        ConnectionResourceRestTest.class,

        /*-----------------------------
            EVENT
        -----------------------------*/
//        EventResourceRestTest.class,

        /*-----------------------------
            CHECKIN
        -----------------------------*/

        /*-----------------------------
            RIDE
        -----------------------------*/
//        RideResourceRestTest.class,

        /*-----------------------------
            NOTIFY
        -----------------------------*/
        //NotificationResourceRestTest.class,

        /*-----------------------------
            STATS
        -----------------------------*/
        //NotificationResourceRestTest.class,
})
public class AppTestSuite {

    @ClassRule
    public static DropwizardAppRule<AppConfiguration> service = new DropwizardAppRule<AppConfiguration>(Service.class, "env/development/conf.yml");

}
