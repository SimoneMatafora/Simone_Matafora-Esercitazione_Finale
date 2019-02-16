package it.tcgroup.vilear.dummy.common.util.operation.impl;

import it.tcgroup.vilear.dummy.common.util.operation.Operation;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Driver;

@Component("DatabaseMigrationOperation")
public class DatabaseMigrationOperation implements Operation {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseMigrationOperation.class);

    @Value("${spring.datasource.driverClassName:#{null}}")
    private String dbDriver;

    @Value("${spring.datasource.url:#{null}}")
    private String dbURL;

    @Value("${migration.init:false}")
    private boolean initOnMigrate;

    @Override
    public void run() throws Exception {

        if (StringUtils.isAnyEmpty(dbDriver, dbURL)) {
            throw new InternalException("You need to correctly define the following system properties: "
                    + "'spring.datasource.driverClassName', "
                    + "'spring.datasource.url'");
        }

        migrate(dbDriver, dbURL);
        LOGGER.info("Migration completed!");
    }

    private void migrate(String driverName, String jdbcUrl) throws Exception {
        Class driver = Class.forName(driverName);
        Driver jdbcDriver = (Driver) driver.newInstance();
        DataSource dataSource = new SimpleDriverDataSource(jdbcDriver, jdbcUrl);
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setInitOnMigrate(initOnMigrate);
        flyway.migrate();
    }
}
