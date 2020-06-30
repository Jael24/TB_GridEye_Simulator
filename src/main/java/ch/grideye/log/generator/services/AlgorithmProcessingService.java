package ch.grideye.log.generator.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class AlgorithmProcessingService {

    private final LoggerService logger;
    private final Random random;

    public AlgorithmProcessingService(final LoggerService logger) {
        this.logger = logger;
        this.random = new Random();
    }

    @Scheduled(cron = "0 0/25 * * * ?")
    public void onMappingValidation() {
        logger.debug(getClass(), "Start of mapping validation !");
        cpuWorker(1000, 1000);
        if (random.nextBoolean()) {
            logger.info(getClass(), "Mapping successfully validated.");
        } else {
            logger.warn(getClass(), "Mapping validation failed.");
        }
    }

    @Scheduled(cron = "0 2 */6 * * ?")
    public void onGpiComputation() {
        logger.debug(getClass(), "Start of GPI computation !");
        cpuWorker(500, 2000);
        logger.info(getClass(), "GPI successfully computed.");
    }

    @Scheduled(cron = "0 3 */6 * * ?")
    public void onPhasorComputation() {
        logger.debug(getClass(), "Start of Phasor computation !");
        cpuWorker(500, 2000);
        logger.info(getClass(), "Phasor successfully computed.");
    }

    private void cpuWorker(final int base, final int worker) {
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < base; ++i) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < worker; ++j) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        logger.error(getClass(), "Worker delay failed !");
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.error(getClass(), "Worker delay failed");
            }
        }
    }
}
