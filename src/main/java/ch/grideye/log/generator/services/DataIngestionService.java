package ch.grideye.log.generator.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataIngestionService {

    private final LoggerService logger;
    private final Random random;
    private int counter = 0;

    public DataIngestionService(final LoggerService logger) {
        this.logger = logger;
        this.random = new Random();
    }

    @Scheduled(cron = "0 * * * * ?")
    public void onDataInsertion() {
        for (int i = 0; i < 10; ++i) {
            String gridEyeId = String.format("tst%02d", i);
            if (counter % 6 == 0 && random.nextInt(20) == 0) {
                logger.warn(getClass(), gridEyeId, "-", "Invalid data insertion, discarding !");
            } else {
                logger.info(getClass(), gridEyeId, "-", "Inserting data !");
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                logger.error(getClass(), gridEyeId, "-", "Delay during insertion failed !");
            }
        }
        ++counter;
    }

    @Scheduled(cron = "0 0/7 * * * ?")
    public void onDataInsertionFromUnknownSource() {
        String gridEyeId = String.format("tst%02d", ThreadLocalRandom.current().nextInt(30, 60));
        logger.error(getClass(), gridEyeId, "-", "Unknown source for data insertion, discarding !");
    }
}
