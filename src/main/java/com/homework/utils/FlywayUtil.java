package com.homework.utils;

import org.flywaydb.core.Flyway;

public class FlywayUtil {
    private FlywayUtil() {}

    public static void startFlyway(DbConfigUtil.PropertyType propertyType){
        Flyway.configure()
                .dataSource(
                        DbConfigUtil.getDbUrl(propertyType),
                        DbConfigUtil.getDbUser(propertyType),
                        DbConfigUtil.getDbPassword(propertyType)
                )
                .baselineOnMigrate(true)
                .load()
                .migrate();
    }
}
