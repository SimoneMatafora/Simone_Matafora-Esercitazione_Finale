package it.tcgroup.vilear.dummy.common.util.operation;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupOperation {

    private static Logger LOGGER = LoggerFactory.getLogger(StartupOperation.class);

    @Value("${migration.enable:false}")
    private boolean migrationEnabled;

    @Autowired(required = false)
    @Qualifier("DatabaseMigrationOperation")
    private Operation databaseMigration;

    @EventListener(ApplicationReadyEvent.class)
    public void runOnStartup() throws Exception {

        LOGGER.info("Check startup operations...");

        if (!migrationEnabled) {
            LOGGER.info("No startup operations to execute");
            return;
        }

        if (migrationEnabled) {
            if (databaseMigration == null) {
                throw new InternalException("Required 'DatabaseMigrationOperation' Component to execute Database Migration");
            }
            LOGGER.info("Start Database migration");
            databaseMigration.run();
            LOGGER.info("Stop Database migration");
        }

        // Stop Spring Application to avoid forgetting operations properties on true mode
        System.exit(0);
    }
}