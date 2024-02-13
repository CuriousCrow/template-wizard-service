package org.curiouscrow.wizardservice.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Regular task for deleting expired template zip-archives
 */
@Component
public class DeleteOldTemplatesTask {

    private static final Logger logger = Logger.getLogger(DeleteOldTemplatesTask.class.getName());

    private static final long TTL = 30000;

    private static final long EXEC_DELAY = 1000;

    @Scheduled(fixedDelay = EXEC_DELAY)
    private void task() throws IOException {

        Path resultFolder = Paths.get("results");
        if (!Files.exists(resultFolder)) {
            return;
        }
        Files.newDirectoryStream(resultFolder).forEach(file -> {

            try {
                BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                long nowMilli = (new Date()).toInstant().toEpochMilli();
                long createdMilli = attr.creationTime().toMillis();
                long sinceCreated = nowMilli - createdMilli;

                if (sinceCreated > TTL) {
                    logger.info(String.format("Deleting expired template file \"%s\"", file));
                    Files.deleteIfExists(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
