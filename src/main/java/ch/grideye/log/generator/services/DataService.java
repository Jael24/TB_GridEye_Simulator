package ch.grideye.log.generator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataService {

    @Autowired
    private LoggerService logger;

    @Scheduled(cron = "0 * * * * ?")
    public void insertValidData() {
        for (int i = 0; i < 10; ++i) {
            String gridEyeId = String.format("tst%02d", i);
            logger.info(getClass(), gridEyeId, "-", "Inserting data !");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                logger.error(getClass(), gridEyeId, "-", "Delay during insertion failed !");
            }
        }
    }

    @Scheduled(cron = "0 0/3 * * * ?")
    public void insertInvalidData() {
        String gridEyeId = String.format("tst%02d", ThreadLocalRandom.current().nextInt(0, 10));
        logger.warn(getClass(), gridEyeId, "-", "Invalid data insertion, discarding !");
    }

    @Scheduled(cron = "0 0/7 * * * ?")
    public void insertDataFromUnknownSource() {
        String gridEyeId = String.format("tst%02d", ThreadLocalRandom.current().nextInt(30, 60));
        logger.error(getClass(), gridEyeId, "-", "Unknown source for data insertion, discarding !");
    }
}
