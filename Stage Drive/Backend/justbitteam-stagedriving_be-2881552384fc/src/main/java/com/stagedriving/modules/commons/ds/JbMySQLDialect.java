package com.stagedriving.modules.commons.ds;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

/**
 * Created by simone on 09/07/14.
 */
public class JbMySQLDialect extends MySQLDialect {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JbMySQLDialect.class);

    public JbMySQLDialect() {
        super();

//        registerFunction("date_sub_days", new SQLFunctionTemplate(StandardBasicTypes.DATE, "date_sub(?1, interval ?2 day)"));
        registerFunction("date_add", new StandardSQLFunction("date_add", StandardBasicTypes.DATE));
        registerFunction("date_add_interval", new SQLFunctionTemplate(StandardBasicTypes.DATE, "date_add(?1, INTERVAL ?2 ?3)"));
        registerFunction("date_sub_interval", new SQLFunctionTemplate(StandardBasicTypes.DATE, "date_sub(?1, INTERVAL ?2 ?3)"));

        registerFunction("haversine", new SQLFunctionTemplate(StandardBasicTypes.FLOAT, "DEGREES(ACOS(COS(RADIANS(?1)) * COS(RADIANS(?2)) * COS(RADIANS(?3) - RADIANS(?4)) + SIN(RADIANS(?5)) * SIN(RADIANS(?6)) ))"));
    }
}


